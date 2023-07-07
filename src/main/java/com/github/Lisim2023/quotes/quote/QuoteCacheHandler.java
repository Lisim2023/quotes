package com.github.Lisim2023.quotes.quote;


import com.github.Lisim2023.quotes.common.CacheHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *  引用数据缓存操作接口
 * @param <T> 缓存数据类型
 *
 * @author Lisim
 */
public interface QuoteCacheHandler<T> extends CacheHandler {

    // 缓存数据, 并设置过期时间
    void cache(String key, T data, long seconds);
    void cache(Map<String, T> data, long seconds);

    // 从缓存移除数据
    Long remove(String key);
    Long remove(Collection<String> keys);

    // 从缓存加载数据, 同时延长过期时间
    T load(String key, long seconds);
    List<T> load(Collection<String> keys, long seconds);

    // 延长过期时间
    Long expire(String key, long seconds);


    // 暂时没有用到
    void cache(String key, T data);
    T load(String key);
}
