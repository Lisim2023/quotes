package io.github.lisim2023.quotes.quote;

import io.github.lisim2023.quotes.base.AbstractHelper;
import io.github.lisim2023.quotes.base.DynamicObjectAccessor;
import io.github.lisim2023.quotes.common.DefaultNoCache;
import io.github.lisim2023.quotes.exceptions.QuoteKeyMissingException;

import java.util.*;

/**
 * <p>
 *  数据引用助手的默认实现
 * </p>
 *
 * @author Lisim
 */
public class QuoteHelperImpl<DynamicO> extends AbstractHelper<DynamicO> implements QuoteHelper {

    public static String QUOTE_INFIX = "_quote_";
    public static String NULL_STRING = "null";
    public static String QUOTE_VALUES_DELIMITER = ",";
    public static String QUOTE_RESULTS_DELIMITER = ",";

    protected QuoteCache quoteCache = new DefaultNoCache();
    protected QuoteDataProvider quoteDataProvider;

    protected QuoteHelperImpl(){

    }

    public QuoteHelperImpl(DynamicObjectAccessor<DynamicO> dynamicObjectAccessor, QuoteDataProvider quoteDataProvider) {
        super(dynamicObjectAccessor);
        this.quoteDataProvider = quoteDataProvider;
    }





    @Override
    public List<Object> appendQuote(Collection<?> records)  {
        if (records == null){
            return null;
        }
        QuoteInfo quoteInfo = createQuoteInfo(records);
        return appendData(records, quoteInfo);
    }

    @Override
    public Object appendQuote(Object record) {
        if (record == null){
            return null;
        }
        QuoteInfo quoteInfo = createQuoteInfo(record);
        return appendData(record, quoteInfo);
    }

    @Override
    public <T> Map<T, Object> appendQuote(Map<T, ?> recordMap){
        if (recordMap == null){
            return null;
        }
        QuoteInfo quoteInfo = createQuoteInfo(recordMap);
        return appendData(recordMap, quoteInfo);
    }




    @Override
    public void cacheQuote(String table, String key, Map<String, Object> quoteData) {
        quoteCache.cacheQuote(table, key, quoteData);
    }

    @Override
    public void cacheQuoteBatch(String table, String key, List<Map<String, Object>> quotesData) {
        quoteCache.cacheQuoteBatch(table, key, quotesData);
    }

    @Override
    public TableData getQuoteBatch(TableData tableData) {
        tableData.getResultMap().putAll(
                getQuoteBatch(tableData.getTable(), tableData.getKey(), tableData.getValueList()));

        return tableData;
    }

    @Override
    public Map<String, Object> getQuote(String table, String key, String value) {
        List<String> values = new ArrayList<>();
        values.add(value);
        return getQuoteBatch(table, key, values).get(value);
    }

    @Override
    public Map<String, Map<String, Object>> getQuoteBatch(String table, String key, List<String> values) {
        Map<String, Map<String, Object>> result = new HashMap<>();
        if (values == null || values.size() == 0){
            return result;
        }

        List<String> valuesLeft = new ArrayList<>(values);

        Map<String, Map<String, Object>> cacheData = quoteCache.loadAndExpireQuoteBatch(table, key, values);
        if (cacheData != null){
            result.putAll(cacheData);
            valuesLeft.removeAll(result.keySet());
        }

        if (valuesLeft.size() > 0){
            List<Map<String, Object>> queryData = quoteDataProvider.queryQuoteData(table, key, valuesLeft);
            if (queryData != null){
                quoteCache.cacheQuoteBatch(table, key, queryData);
                queryData.forEach(map -> {
                    if (map != null){
                        Object keyValue = map.get(key);
                        if (keyValue == null){
                            throw new QuoteKeyMissingException(table, key);
                        }
                        result.put(keyValue.toString(), map);
                    }
                });
            }
        }
        return result;
    }

    @Override
    public Long removeQuote(String table, String key, String value) {
        return quoteCache.removeQuote(table, key, value);
    }

    @Override
    public Long removeQuote(String table, String key, Collection<String> values) {
        return quoteCache.removeQuote(table, key, values);
    }





    protected QuoteInfo createQuoteInfo(Object object){
        QuoteInfo quoteInfo = new QuoteInfo();
        quoteInfo.analyse(object);
        quoteInfo.getQuoteDataMap().replaceAll((k, v) -> getQuoteBatch(quoteInfo.getQuoteDataMap().get(k)));
        return quoteInfo;
    }





    public QuoteCache getQuoteCache() {
        return quoteCache;
    }

    public void setQuoteCache(QuoteCache quoteCache) {
        this.quoteCache = quoteCache;
    }

    public QuoteDataProvider getQuoteDataProvider() {
        return quoteDataProvider;
    }

    public void setQuoteDataProvider(QuoteDataProvider quoteDataProvider) {
        this.quoteDataProvider = quoteDataProvider;
    }
}
