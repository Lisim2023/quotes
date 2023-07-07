package io.github.lisim2023.quotes.dict;

import io.github.lisim2023.quotes.common.CacheSerializer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 字典缓存的默认实现
 * @param <T> 缓存数据类型
 *
 * @author Lisim
 */
public class DictCacheImpl<T> implements DictCache {

    // 缓存前缀
    public static String CACHE_KEY_PREFIX = "quotes:dict";

    private CacheSerializer<T> cacheSerializer;
    private DictCacheHandler<T> dictCacheHandler;


    public DictCacheImpl(CacheSerializer<T> cacheSerializer, DictCacheHandler<T> dictCacheHandler) {
        this.cacheSerializer = cacheSerializer;
        this.dictCacheHandler = dictCacheHandler;
    }

    protected String codeToKey(String dictCode){
        return String.format("%s::%s", CACHE_KEY_PREFIX, dictCode);
    }

    protected String keyToCode(String dictKey){
        return dictKey == null ? null : dictKey.substring(dictKey.lastIndexOf("::"));
    }

    @Override
    public void rename(String oldCode, String newCode) {
        dictCacheHandler.rename(codeToKey(oldCode), codeToKey(newCode));
    }

    @Override
    public void cacheDict(String dictCode, Map<String, Object> dictData) {
        if (dictCode != null && dictData != null){
            T data = cacheSerializer.serialize(dictData);
            String dictKey = codeToKey(dictCode);
            dictCacheHandler.cache(dictKey, data);
        }
    }

    @Override
    public void cacheDictBatch(Map<String, Map<String, Object>> dictData) {
        if (dictData != null){
            Map<String, T> data = new HashMap<>();
            dictData.forEach((key, value) -> {
                if (key != null && value != null && !value.isEmpty()){
                    data.put(codeToKey(key), cacheSerializer.serialize(value));
                }
            });
            dictCacheHandler.cache(data);
        }
    }

    @Override
    public Map<String, Object> loadDict(String dictCode) {
        if (dictCode == null){
            return null;
        }
        String dictKey = codeToKey(dictCode);
        T data = dictCacheHandler.load(dictKey);
        if (data == null){
            return null;
        }
        return cacheSerializer.deSerialize(data);
    }

    @Override
    public Map<String, Map<String, Object>> loadDictBatch(List<String> dictCodes) {
        if (dictCodes == null){
            return null;
        }
        List<String> dictKeys = dictCodes.stream().map(this::codeToKey).collect(Collectors.toList());
        return loadToMap(dictKeys, dictCodes);
    }

    @Override
    public Map<String, Map<String, Object>> loadAllDict(){
        Set<String> keys = dictCacheHandler.keys(CACHE_KEY_PREFIX + "*");
        if (keys == null){
            return null;
        }
        List<String> dictKeys = new ArrayList<>(keys);
        List<String> dictCodes = dictKeys.stream()
                .map(this::keyToCode)
                .collect(Collectors.toList());
        return loadToMap(dictKeys, dictCodes);
    }


    @Override
    public Long removeDict(String dictCode) {
        String dictKey = codeToKey(dictCode);
        return dictCacheHandler.remove(dictKey);
    }

    @Override
    public Long removeDict(Collection<String> dictCodes) {
        if (dictCodes == null){
            return 0L;
        }
        List<String> dictKeys = dictCodes.stream()
                .map(this::codeToKey)
                .collect(Collectors.toList());
        return dictCacheHandler.remove(dictKeys);
    }

    @Override
    public Long removeAllDict() {
        Set<String> keys = dictCacheHandler.keys(CACHE_KEY_PREFIX + "*");
        return dictCacheHandler.remove(keys);
    }




    protected Map<String, Map<String, Object>> loadToMap(List<String> dictKeys, List<String> dictCodes){
        Map<String, Map<String, Object>> dictData = new HashMap<>();

        List<Map<String, Object>> results = loadDeSerialize(dictKeys);
        for (int i = 0; i < results.size(); i++) {
            Map<String, Object> result = results.get(i);
            if (result != null && !result.isEmpty()){
                dictData.put(dictCodes.get(i), result);
            }
        }
        return dictData;
    }

    protected List<Map<String, Object>> loadDeSerialize(Collection<String> dictKeys){
        List<Map<String, Object>> data = new ArrayList<>();
        if (dictKeys == null || dictKeys.size() == 0){
            return data;
        }
        List<T> results = dictCacheHandler.load(dictKeys);
        if (results == null){
            return data;
        }

        for (T result : results) {
            data.add(cacheSerializer.deSerialize(result));
        }
        return data;
    }





    public CacheSerializer<T> getCacheSerializer() {
        return cacheSerializer;
    }

    public void setCacheSerializer(CacheSerializer<T> cacheSerializer) {
        this.cacheSerializer = cacheSerializer;
    }

    public DictCacheHandler<T> getDictCacheHandler() {
        return dictCacheHandler;
    }

    public void setDictCacheHandler(DictCacheHandler<T> dictCacheHandler) {
        this.dictCacheHandler = dictCacheHandler;
    }
}
