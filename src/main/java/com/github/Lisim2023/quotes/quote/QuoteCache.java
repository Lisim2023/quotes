package com.github.Lisim2023.quotes.quote;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  为引用助手提供缓存操作
 * </p>
 *
 * @author Lisim
 */
public interface QuoteCache {

    // 缓存引用数据
    void cacheQuote(String table, String key, Map<String, Object> quoteData);
    void cacheQuoteBatch(String table, String key, List<Map<String, Object>> quotesData);

    // 从缓存加载引用数据, 并延长过期时间
    Map<String, Object> loadAndExpireQuote(String table, String key, String value);
    Map<String, Map<String, Object>> loadAndExpireQuoteBatch(String table, String key, List<String> values);

    // 从缓存移除引用数所
    Long removeQuote(String table, String key, String value);
    Long removeQuote(String table, String key, Collection<String> values);
}
