package io.github.lisim2023.quotes.combo;


import io.github.lisim2023.quotes.base.AbstractHelper;
import io.github.lisim2023.quotes.base.DynamicObjectAccessor;
import io.github.lisim2023.quotes.dict.DictHelper;
import io.github.lisim2023.quotes.quote.QuoteHelper;
import io.github.lisim2023.quotes.quote.TableData;

import java.util.*;

/**
 * 字典助手及引用助手的组合
 * @param <DynamicO> 动态对象类型
 *
 * @author Lisim
 */
public class ComboHelperImpl<DynamicO> extends AbstractHelper<DynamicO> implements ComboHelper {

    protected QuoteHelper quoteHelper;

    protected DictHelper dictHelper;


    protected ComboHelperImpl(){

    }


    public ComboHelperImpl(DynamicObjectAccessor<DynamicO> dynamicObjectAccessor, DictHelper dictHelper, QuoteHelper quoteHelper) {
        super(dynamicObjectAccessor);
        this.dictHelper = dictHelper;
        this.quoteHelper = quoteHelper;
    }

    public Object appendQuoteAndDict(Object record) {
        if (record == null){
            return null;
        }
        ComboInfo comboInfo = createComboInfo(record);
        return appendData(record, comboInfo);
    }

    public List<Object> appendQuoteAndDict(Collection<?> records) {
        if (records == null){
            return null;
        }
        ComboInfo comboInfo = createComboInfo(records);
        return appendData(records, comboInfo);
    }

    @Override
    public <T> Map<T, Object> appendQuoteAndDict(Map<T, ?> recordMap) {
        if (recordMap == null){
            return null;
        }
        ComboInfo comboInfo = createComboInfo(recordMap);
        return appendData(recordMap, comboInfo);
    }





    public Object appendQuote(Object record) {
        return quoteHelper.appendQuote(record);
    }

    public List<Object> appendQuote(Collection<?> records) {
        return quoteHelper.appendQuote(records);
    }

    public <T> Map<T, Object> appendQuote(Map<T, ?> recordMap){
        return quoteHelper.appendQuote(recordMap);
    }


    @Override
    public void cacheQuote(String table, String key, Map<String, Object> quoteData) {
        quoteHelper.cacheQuote(table, key, quoteData);
    }

    @Override
    public void cacheQuoteBatch(String table, String key, List<Map<String, Object>> quotesData) {
        quoteHelper.cacheQuoteBatch(table, key, quotesData);
    }

    @Override
    public Map<String, Object> getQuote(String table, String key, String value) {
        return quoteHelper.getQuote(table, key, value);
    }

    @Override
    public Map<String, Map<String, Object>> getQuoteBatch(String table, String key, List<String> values) {
        return quoteHelper.getQuoteBatch(table, key, values);
    }

    @Override
    public TableData getQuoteBatch(TableData tableData) {
        return quoteHelper.getQuoteBatch(tableData);
    }

    @Override
    public Long removeQuote(String table, String key, String value) {
        return quoteHelper.removeQuote(table, key, value);
    }

    @Override
    public Long removeQuote(String table, String key, Collection<String> values) {
        return quoteHelper.removeQuote(table, key, values);
    }





    @Override
    public Object appendDict(Object record) {
        return dictHelper.appendDict(record);
    }

    @Override
    public List<Object> appendDict(Collection<?> records) {
        return dictHelper.appendDict(records);
    }

    @Override
    public <T> Map<T, Object> appendDict(Map<T, ?> recordMap) {
        return dictHelper.appendDict(recordMap);
    }


    @Override
    public void replaceDictInverse(Map<String, Object> objectData, Class<?> tClass) {
        dictHelper.replaceDictInverse(objectData, tClass);
    }

    @Override
    public void replaceDictInverse(Collection<Map<String, Object>> objectsData, Class<?> tClass) {
        dictHelper.replaceDictInverse(objectsData, tClass);
    }

    @Override
    public void replaceDict(Map<String, Object> objectData, Class<?> tClass) {
        dictHelper.replaceDict(objectData, tClass);
    }

    @Override
    public void replaceDict(Collection<Map<String, Object>> objectsData, Class<?> tClass) {
        dictHelper.replaceDict(objectsData, tClass);
    }

    @Override
    public void cacheDict(String dictCode, Map<String, Object> dictData) {
        dictHelper.cacheDict(dictCode, dictData);
    }

    @Override
    public void cacheDictBatch(Map<String, Map<String, Object>> dictData) {
        dictHelper.cacheDictBatch(dictData);
    }

    @Override
    public Map<String, Object> getDict(String dictCode) {
        return dictHelper.getDict(dictCode);
    }

    @Override
    public Map<String, Object> getDict(String dictCode, boolean inverse) {
        return dictHelper.getDict(dictCode, inverse);
    }

    @Override
    public Map<String, Map<String, Object>> getDictBatch(List<String> dictCodeList) {
        return dictHelper.getDictBatch(dictCodeList);
    }

    @Override
    public Map<String, Map<String, Object>> getDictBatch(List<String> dictCodeList, boolean inverse) {
        return dictHelper.getDictBatch(dictCodeList, inverse);
    }

    @Override
    public Long removeDict(String dictCode) {
        return dictHelper.removeDict(dictCode);
    }

    @Override
    public Long removeDict(Collection<String> dictCodes) {
        return dictHelper.removeDict(dictCodes);
    }

    @Override
    public Long removeAllDict() {
        return dictHelper.removeAllDict();
    }

    @Override
    public void cacheAllDict() {
        dictHelper.cacheAllDict();
    }

    @Override
    public Map<String, Map<String, Object>> loadAllDict(){
        return dictHelper.loadAllDict();
    }

    @Override
    public void rename(String oldCode, String newCode) {
        dictHelper.rename(oldCode, newCode);
    }





    protected ComboInfo createComboInfo(Object object){
        ComboInfo comboInfo = new ComboInfo();
        comboInfo.analyse(object);
        comboInfo.getQuoteDataMap().replaceAll((k, v) -> getQuoteBatch(comboInfo.getQuoteDataMap().get(k)));
        comboInfo.setDictDataMap(getDictBatch(comboInfo.getDictCodeList(), false));
        return comboInfo;
    }





    public QuoteHelper getQuoteHelper() {
        return quoteHelper;
    }

    public void setQuoteHelper(QuoteHelper quoteHelper) {
        this.quoteHelper = quoteHelper;
    }

    public DictHelper getDictionaryHelper() {
        return dictHelper;
    }

    public void setDictionaryHelper(DictHelper dictHelper) {
        this.dictHelper = dictHelper;
    }
}
