package com.github.Lisim2023.quotes.common;


import com.github.Lisim2023.quotes.exceptions.SerializationException;

import java.util.Map;

/**
 * 缓存序列化接口
 * @param <T> 数据类型
 *
 * @author Lisim
 */
public interface CacheSerializer<T> {

    // 将Map数据序列化为指定数据类型T, 之后由CacheHandler完成缓存
    T serialize(Map<String, Object> dataToCache) throws SerializationException;

    // 将从CacheHandler获取的指定数据类型T反序列化为Map
    Map<String, Object> deSerialize(T cacheData) throws SerializationException;
}
