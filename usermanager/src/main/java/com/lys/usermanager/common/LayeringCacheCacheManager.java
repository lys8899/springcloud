package com.lys.usermanager.common;


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;
import java.util.Collections;

/**
 * @description: 二级缓存管理
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-11-06 08:44
 **/
public class LayeringCacheCacheManager extends AbstractCacheManager {


    private CacheManager redisCacheManager;

    public LayeringCacheCacheManager(CacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return Collections.emptySet();
    }


    @Override
    protected Cache getMissingCache(String name) {
        return new LayeringCache(false, name, redisCacheManager);
    }


}
