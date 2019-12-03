package com.lys.usermanager.config;

import com.lys.usermanager.common.LayeringCacheCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;


/**
 * @description: 缓存管理
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-11-04 08:46
 **/

@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfigAll {

    public static final String SIMPLE_CACHE_MANAGER = "simpleCacheManager";
    public static final String REDIS_CACHE_MANAGER = "redisCacheManager";
    public static final String LAYERING_CACHE_MANAGER = "LayeringCacheManager";


    @Bean
    GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }


    @Bean(REDIS_CACHE_MANAGER)
    CacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory, CacheProperties cacheProperties) {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        config = config.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer()));
        config = config.disableCachingNullValues();
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return builder.cacheDefaults(config).build();
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Primary
    @Bean(CacheConfigAll.SIMPLE_CACHE_MANAGER)
    CacheManager simpleCacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        concurrentMapCacheManager.setAllowNullValues(false);
        return concurrentMapCacheManager;
    }


    @Bean(LAYERING_CACHE_MANAGER)
    LayeringCacheCacheManager layeringCacheCacheManager(@Autowired @Qualifier(REDIS_CACHE_MANAGER) CacheManager cacheManager) {
        return new LayeringCacheCacheManager(cacheManager);
    }


}