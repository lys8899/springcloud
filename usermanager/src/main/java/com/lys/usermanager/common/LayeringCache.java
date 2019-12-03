package com.lys.usermanager.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

/**
 * @description: 两级缓存
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-11-05 16:26
 **/
public class LayeringCache extends AbstractValueAdaptingCache {

    private Logger logger = LoggerFactory.getLogger(LayeringCache.class);
    /**
     * 缓存的名称
     */
    private String name;

    /**
     * redi缓存
     */
    private RedisCache redisCache;

    /**
     * 本地缓存
     */
    private ConcurrentMapCache concurrentMapCache;


    public LayeringCache(boolean allowNullValues, String name, CacheManager redisCacheManager) {
        super(allowNullValues);
        this.name = name;
        try {
            Field redisCacheWriter = RedisCacheManager.class.getDeclaredField("cacheWriter");
            Field cacheConfiguration = RedisCacheManager.class.getDeclaredField("defaultCacheConfig");
            redisCacheWriter.setAccessible(true);
            cacheConfiguration.setAccessible(true);
            Constructor<RedisCache> constructor =
                    RedisCache.class.getDeclaredConstructor(String.class, RedisCacheWriter.class, RedisCacheConfiguration.class);
            constructor.setAccessible(true);
            RedisCache redisCache = constructor.newInstance(name, redisCacheWriter.get(redisCacheManager), cacheConfiguration.get(redisCacheManager));
            this.redisCache = redisCache;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        this.concurrentMapCache = new ConcurrentMapCache(name);

    }


    @Override
    protected Object lookup(Object key) {
        Object o = concurrentMapCache.getNativeCache().get(key);
        logger.debug("lookup查询到一级缓存 key:{}", key);
        if (o == null) {
            ValueWrapper wrapper = redisCache.get(key);
            logger.debug("lookup查询到二级缓存 key:{}", key);
            if (wrapper != null) {
                synchronized (concurrentMapCache) {
                    Object t2 = concurrentMapCache.get(key);
                    if (t2 == null) {
                        concurrentMapCache.put(key, wrapper.get());
                    }
                }
                return wrapper.get();
            }
        }
        return o;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        T t = concurrentMapCache.get(key, valueLoader);
        if (t != null) {
            logger.debug("get查询到一级缓存 key:{}", key);
            return t;
        }
        T t1 = redisCache.get(key, valueLoader);
        logger.debug("get查询到二级缓存 key:{}", key);

        synchronized (concurrentMapCache) {
            T t2 = concurrentMapCache.get(key, valueLoader);
            if (t2 == null) {
                concurrentMapCache.put(key, t1);
            }
        }
        return t1;
    }

    @Override
    public void put(Object key, Object value) {
        logger.debug("put concurrentMapCache缓存 key:{}", key);
        concurrentMapCache.put(key, value);
        logger.debug("put redisCache key:{}", key);
        redisCache.put(key, value);
    }

    @Override
    public void evict(Object key) {
        concurrentMapCache.evict(key);
        redisCache.evict(key);
    }

    @Override
    public void clear() {
        concurrentMapCache.clear();
        redisCache.clear();
    }
}
