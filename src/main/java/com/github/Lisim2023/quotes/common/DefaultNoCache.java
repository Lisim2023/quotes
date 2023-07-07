package com.github.Lisim2023.quotes.common;

import com.github.Lisim2023.quotes.dict.DictCache;
import com.github.Lisim2023.quotes.quote.QuoteCache;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  字典和引用数据默认都不缓存
 * </p>
 *
 * @author Lisim
 */
public class DefaultNoCache implements DictCache, QuoteCache {

    @Override
    public void rename(String oldKey, String newKey) {

    }

    @Override
    public void cacheDict(String dictCode, Map<String, Object> dictData) {

    }

    @Override
    public void cacheDictBatch(Map<String, Map<String, Object>> dictData) {

    }

    @Override
    public Map<String, Object> loadDict(String dictCode) {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> loadDictBatch(List<String> dictCodes) {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> loadAllDict(){
        return null;
    }

    @Override
    public Long removeDict(String dictCode) {
        return null;
    }

    @Override
    public Long removeDict(Collection<String> dictCodes) {
        return null;
    }

    @Override
    public Long removeAllDict() {
        return null;
    }






    @Override
    public void cacheQuote(String table, String key, Map<String, Object> quoteData) {

    }

    @Override
    public void cacheQuoteBatch(String table, String key, List<Map<String, Object>> quotesData) {

    }

    @Override
    public Map<String, Object> loadAndExpireQuote(String table, String key, String value) {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> loadAndExpireQuoteBatch(String table, String key, List<String> values) {
        return null;
    }

    @Override
    public Long removeQuote(String table, String key, String value) {
        return null;
    }

    @Override
    public Long removeQuote(String table, String key, Collection<String> values) {
        return null;
    }




}
