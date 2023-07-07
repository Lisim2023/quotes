package io.github.lisim2023.quotes.dict;


import io.github.lisim2023.quotes.base.AbstractHelper;
import io.github.lisim2023.quotes.common.CommonUtil;
import io.github.lisim2023.quotes.common.DefaultNoCache;
import io.github.lisim2023.quotes.base.DynamicObjectAccessor;

import java.util.*;


/**
 * <P>
 *   数据字典助手的默认实现
 * </P>
 *
 * @author Lisim
 */
public class DictHelperImpl<DynamicO> extends AbstractHelper<DynamicO> implements DictHelper {

    public static String DICT_SUFFIX = "_dictLabel";
    public static String DICT_VALUES_DELIMITER = ",";
    public static String DICT_RESULTS_DELIMITER = ",";

    protected DictCache dictCache = new DefaultNoCache();
    protected DictDataProvider dictDataProvider;

    protected DictHelperImpl(){}

    public DictHelperImpl(DynamicObjectAccessor<DynamicO> dynamicObjectAccessor, DictDataProvider dictDataProvider) {
        super(dynamicObjectAccessor);
        this.dictDataProvider = dictDataProvider;
    }





    @Override
    public Object appendDict(Object record) {
        if (record == null){
            return null;
        }
        DictInfo dictInfo = createDictInfo(record);
        return appendData(record, dictInfo);
    }

    @Override
    public List<Object> appendDict(Collection<?> records) {
        if (records == null){
            return null;
        }
        DictInfo dictInfo = createDictInfo(records);
        return appendData(records, dictInfo);
    }

    @Override
    public <T> Map<T, Object> appendDict(Map<T, ?> recordMap) {
        if (recordMap == null){
            return null;
        }
        DictInfo dictInfo = createDictInfo(recordMap);
        return appendData(recordMap, dictInfo);
    }

    @Override
    public void replaceDict(Map<String, Object> beanData, Class<?> tClass){
        replaceDict(beanData, tClass, false);
    }

    @Override
    public void replaceDict(Collection<Map<String, Object>> beansData, Class<?> tClass) {
        replaceDict(beansData, tClass, false);
    }

    @Override
    public void replaceDictInverse(Map<String, Object> beanData, Class<?> tClass) {
        replaceDict(beanData, tClass, true);
    }

    @Override
    public void replaceDictInverse(Collection<Map<String, Object>> beansData, Class<?> tClass) {
        replaceDict(beansData, tClass, true);
    }

    protected void replaceDict(Map<String, Object> beanData, Class<?> tClass, boolean inverse){
        if (beanData == null){
            return;
        }
        DictInfo dictInfo = new DictInfo();
        dictInfo.analyse(tClass);
        dictInfo.setDictDataMap(getDictBatch(dictInfo.getDictCodeList(), inverse));

        dictInfo.replaceProperties(beanData, tClass);
    }

    protected void replaceDict(Collection<Map<String, Object>> beansData, Class<?> tClass, boolean inverse){
        if (beansData == null){
            return;
        }
        DictInfo dictInfo = new DictInfo();
        dictInfo.analyse(tClass);
        dictInfo.setDictDataMap(getDictBatch(dictInfo.getDictCodeList(), inverse));

        for (Map<String, Object> beanData : beansData) {
            dictInfo.replaceProperties(beanData, tClass);
        }
    }


    @Override
    public void rename(String oldCode, String newCode) {
        dictCache.rename(oldCode, newCode);
    }

    @Override
    public void cacheDict(String dictCode, Map<String, Object> dictData) {
        dictCache.cacheDict(dictCode, dictData);
    }

    @Override
    public void cacheDictBatch(Map<String, Map<String, Object>> dictData) {
        dictCache.cacheDictBatch(dictData);
    }

    @Override
    public Map<String, Object> getDict(String dictCode){
        return getDict(dictCode, false);
    }

    @Override
    public Map<String, Object> getDict(String dictCode, boolean inverse) {
        List<String> dictCodes = new ArrayList<>();
        dictCodes.add(dictCode);
        return getDictBatch(dictCodes, inverse).get(dictCode);
    }

    @Override
    public Long removeDict(String dictCode) {
        return dictCache.removeDict(dictCode);
    }

    @Override
    public Long removeDict(Collection<String> dictCodes) {
        return dictCache.removeDict(dictCodes);
    }

    @Override
    public Long removeAllDict() {
        return dictCache.removeAllDict();
    }

    @Override
    public void cacheAllDict() {
        Map<String, Map<String, Object>> allDictData = dictDataProvider.queryAllDictData();
        if (allDictData != null){
            dictCache.cacheDictBatch(allDictData);
        }
    }

    @Override
    public Map<String, Map<String, Object>> loadAllDict(){
        return dictCache.loadAllDict();
    }

    @Override
    public Map<String, Map<String, Object>> getDictBatch(List<String> dictCodeList, boolean inverse){
        Map<String, Map<String, Object>> dictDataMap = getDictBatch(dictCodeList);
        if (inverse){
            dictDataMap.replaceAll((k, v) -> inverseMap(dictDataMap.get(k)));
        }
        return dictDataMap;
    }

    public Map<String, Map<String, Object>> getDictBatch(List<String> dictCodeList){
        Map<String, Map<String, Object>> result = new HashMap<>();
        if (dictCodeList == null || dictCodeList.size() == 0) {
            return result;
        }

        List<String> codesLeft = new ArrayList<>(dictCodeList);

        Map<String, Map<String, Object>> cacheData = dictCache.loadDictBatch(dictCodeList);
        if (cacheData != null) {
            result.putAll(cacheData);
            codesLeft.removeAll(cacheData.keySet());
        }

        if (codesLeft.size() > 0){
            Map<String, Map<String, Object>> queryData = dictDataProvider.queryDictData(codesLeft);
            if (queryData != null){
                dictCache.cacheDictBatch(queryData);
                result.putAll(queryData);
            }
        }

        return result;
    }





    protected Map<String, Object> inverseMap(Map<String, Object> dataMap){
        if (dataMap == null) {
            return null;
        }
        Map<String, Object> inverseMap = new HashMap<>();
        dataMap.forEach((key, value) -> inverseMap.put(CommonUtil.toString(value), key));
        return inverseMap;
    }





    protected DictInfo createDictInfo(Object object){
        DictInfo dictInfo = new DictInfo();
        dictInfo.analyse(object);
        dictInfo.setDictDataMap(getDictBatch(dictInfo.getDictCodeList()));
        return dictInfo;
    }


    public DictCache getDictCache() {
        return dictCache;
    }

    public void setDictCache(DictCache dictCache) {
        this.dictCache = dictCache;
    }

    public DictDataProvider getDictDataProvider() {
        return dictDataProvider;
    }

    public void setDictDataProvider(DictDataProvider dictDataProvider) {
        this.dictDataProvider = dictDataProvider;
    }
}
