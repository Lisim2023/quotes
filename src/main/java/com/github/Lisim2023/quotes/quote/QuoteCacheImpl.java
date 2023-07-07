package com.github.Lisim2023.quotes.quote;

import com.github.Lisim2023.quotes.common.CacheSerializer;
import com.github.Lisim2023.quotes.common.CommonUtil;
import com.github.Lisim2023.quotes.exceptions.QuoteKeyMissingException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 引用缓存的默认实现
 * @param <T> 缓存数据类型
 *
 * @author Lisim
 */
public class QuoteCacheImpl<T> implements QuoteCache {

    // 缓存前缀
    public static String CACHE_KEY_PREFIX = "quotes:quote";
    // 默认缓存时间(秒)
    public static Long DEFAULT_CACHE_SECONDS = 30 * 60L;
    // 表缓存时间(表名: 秒)
    public static Map<String, Long> TABLE_CACHE_SECONDS = new HashMap<>();



    private CacheSerializer<T> cacheSerializer;
    private QuoteCacheHandler<T> quoteCacheHandler;


    public QuoteCacheImpl(CacheSerializer<T> cacheSerializer, QuoteCacheHandler<T> quoteCacheHandler) {
        this.cacheSerializer = cacheSerializer;
        this.quoteCacheHandler = quoteCacheHandler;
    }


    @Override
    public void cacheQuote(String table, String key, Map<String, Object> quoteData) {
        long seconds = getTableCacheTime(table);
        if (seconds <= 0) {
            return;
        }
        String value = getValueOfKeyColumn(table, key, quoteData);
        String cacheKey = formatQuoteKey(table, key, value);
        T data = cacheSerializer.serialize(quoteData);
        quoteCacheHandler.cache(cacheKey, data, seconds);
    }

    @Override
    public void cacheQuoteBatch(String table, String key, List<Map<String, Object>> quoteData) {
        if (quoteData != null){
            long seconds = getTableCacheTime(table);
            if (seconds <= 0) {
                return;
            }
            Map<String, T> data = new HashMap<>();
            for (Map<String, Object> quoteDatum : quoteData) {
                String value = getValueOfKeyColumn(table, key, quoteDatum);
                String cacheKey = formatQuoteKey(table, key, value);
                T cacheData = cacheSerializer.serialize(quoteDatum);
                data.put(cacheKey, cacheData);
            }
            quoteCacheHandler.cache(data, seconds);
        }
    }

    @Override
    public Long removeQuote(String table, String key, String value) {
        String cacheKey = formatQuoteKey(table, key, value);
        return quoteCacheHandler.remove(cacheKey);
    }

    @Override
    public Long removeQuote(String table, String key, Collection<String> values) {
        if (values == null || values.size() == 0){
            return 0L;
        }
        List<String> cacheKeys = values.stream()
                .map(value -> this.formatQuoteKey(table, key, value))
                .collect(Collectors.toList());
        return quoteCacheHandler.remove(cacheKeys);
    }

    @Override
    public Map<String, Object> loadAndExpireQuote(String table, String key, String value) {
        long seconds = getTableCacheTime(table);
        if (seconds <= 0){
            return null;
        }
        String cacheKey = formatQuoteKey(table, key, value);
        T data = quoteCacheHandler.load(cacheKey, seconds);
        if (data == null){
            return null;
        }
        return cacheSerializer.deSerialize(data);
    }

    @Override
    public Map<String, Map<String, Object>> loadAndExpireQuoteBatch(String table, String key, List<String> values){
        if (values == null || values.size() == 0){
            return null;
        }
        long seconds = getTableCacheTime(table);
        if (seconds <= 0) {
            return null;
        }
        Map<String, Map<String, Object>> quoteData = new HashMap<>();
        List<String> cacheKeys = values.stream()
                .map(value -> formatQuoteKey(table, key, value))
                .collect(Collectors.toList());

        List<Map<String, Object>> results = loadDeSerialize(cacheKeys, seconds);
        results.forEach(map -> {
            if (map != null){
                quoteData.put(CommonUtil.toString(map.get(key)), map);
            }
        });
        return quoteData;
    }

    protected List<Map<String, Object>> loadDeSerialize(List<String> cacheKeys, long seconds){
        List<Map<String, Object>> result = new ArrayList<>();
        List<T> data = quoteCacheHandler.load(cacheKeys, seconds);
        if (data == null){
            return result;
        }
        for (T t : data) {
            result.add(cacheSerializer.deSerialize(t));
        }
        return result;
    }



    protected Long getTableCacheTime(String table){
        Long seconds = TABLE_CACHE_SECONDS.get(table);
        return seconds == null ? DEFAULT_CACHE_SECONDS : seconds;
    }



    protected String getValueOfKeyColumn(String table, String key, Map<String, Object> quoteData){
        Object value = quoteData.get(key);
        if (value == null){
            throw new QuoteKeyMissingException(table, key);
        }
        return value.toString();
    }

    protected String formatQuoteKey(String table, String key, String value){
        return String.format("%s:%s:%s::%s", CACHE_KEY_PREFIX, table, key, value);
    }


    public CacheSerializer<T> getCacheSerialize() {
        return cacheSerializer;
    }

    public void setCacheSerialize(CacheSerializer<T> cacheSerializer) {
        this.cacheSerializer = cacheSerializer;
    }

    public QuoteCacheHandler<T> getQuoteCacheHandler() {
        return quoteCacheHandler;
    }

    public void setQuoteCacheHandler(QuoteCacheHandler<T> quoteCacheHandler) {
        this.quoteCacheHandler = quoteCacheHandler;
    }


}
