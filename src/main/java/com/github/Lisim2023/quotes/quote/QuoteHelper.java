package com.github.Lisim2023.quotes.quote;


import com.github.Lisim2023.quotes.exceptions.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  数据引用助手, 功能包括:
 *  1.为数据对象追加从关联表引用的数据
 *  2.提供引用数据的缓存相关操作
 * </p>
 *
 * @author Lisim
 */
public interface QuoteHelper {

    // 将实体类对象转换为动态对象, 并追加引用的数据
    Object appendQuote(Object record) throws
            QuoteNotFoundException,
            QuoteKeyMissingException,
            IllegalAccessRunTimeException,
            DynamicObjectAccessException,
            SerializationException;

    // 集合类数据的返回值统一使用ArrayList
    List<Object> appendQuote(Collection<?> records) throws
            QuoteNotFoundException,
            QuoteKeyMissingException,
            IllegalAccessRunTimeException,
            DynamicObjectAccessException,
            SerializationException;

    <T> Map<T, Object> appendQuote(Map<T, ?> recordMap) throws
            QuoteNotFoundException,
            QuoteKeyMissingException,
            IllegalAccessRunTimeException,
            DynamicObjectAccessException,
            SerializationException;


    // 缓存引用数据
    void cacheQuote(String table, String key, Map<String, Object> quoteData) throws QuoteKeyMissingException;
    void cacheQuoteBatch(String table, String key, List<Map<String, Object>> quotesData) throws QuoteKeyMissingException;

    // 从缓存移除引用数据
    Long removeQuote(String table, String key, String value);
    Long removeQuote(String table, String key, Collection<String> values);

    // 读取引用数据, 优先缓存
    // 缓存未命中会调用QuoteDataProvider获取, 并缓存这部分数据
    Map<String, Object> getQuote(String table, String key, String value) throws QuoteKeyMissingException;
    Map<String, Map<String, Object>> getQuoteBatch(String table, String key, List<String> values) throws QuoteKeyMissingException;
    TableData getQuoteBatch(TableData tableData) throws QuoteKeyMissingException;
}
