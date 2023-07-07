package com.github.Lisim2023.quotes.dict;

import com.github.Lisim2023.quotes.common.CacheHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 字典数据缓存操作接口
 * @param <T> 缓存数据类型
 * @author Lisim
 */
public interface DictCacheHandler<T> extends CacheHandler {


    void cache(String key, T data);
    void cache(Map<String, T> data);


    Long remove(String key);
    Long remove(Collection<String> keys);


    T load(String key);
    List<T> load(Collection<String> keys);


    void rename(String oldKey, String newKey);


    Set<String> keys(String pattern);

}
