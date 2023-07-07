package io.github.lisim2023.quotes.dict;

import io.github.lisim2023.quotes.exceptions.DictNotFoundException;
import io.github.lisim2023.quotes.exceptions.DictValueNotFoundException;
import io.github.lisim2023.quotes.exceptions.IllegalAccessRunTimeException;
import io.github.lisim2023.quotes.base.AbstractStructure;
import io.github.lisim2023.quotes.common.CommonUtil;
import io.github.lisim2023.quotes.quote.QuoteInfo;


import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <P>
 *   字典任务相关信息
 *   1.收集字典任务中涉及到的数据信息, 以便从缓存及数据库批量查询相关数据.
 *   2.将查询到的数据保存到此对象, 之后在任务过程中由此对象提供数据.
 * </P>
 *
 * @author Lisim
 */
public class DictInfo extends AbstractStructure {

    private List<String> dictCodeList = new ArrayList<>();
    private Map<Class<?>, List<Field>> classDictFieldsMap = new HashMap<>();
    private Map<String, Map<String, Object>> dictDataMap = new HashMap<>();


    public DictInfo(){

    }


    public DictInfo(QuoteInfo quoteInfo){
        this.classAllFieldsMap = quoteInfo.getClassAllFieldsMap();
        this.classRecursionFieldsMap = quoteInfo.getClassRecursionFieldsMap();
        this.analyse();
    }


    public void analyse(Class<?> clazz) {
        this.classRecursionFieldsMap.put(clazz, new ArrayList<>());
        this.analyse();
    }


    @Override
    public void analyse(Object record) {
        super.analyse(record);
        this.analyse();
    }


    public void analyse(){
        this.classDictFieldsMap = collectAnnotationFields(Dict.class);
        this.dictCodeList = collectDictCodes();
    }

    protected List<String> collectDictCodes(){
        List<String> dictCodeList = new ArrayList<>();
        for (List<Field> dictFieldList : this.classDictFieldsMap.values()) {
            List<String> codes = dictFieldList.stream()
                    .map(field -> field.getAnnotation(Dict.class).value())
                    .distinct()
                    .collect(Collectors.toList());
            dictCodeList.addAll(codes);
        }
        return dictCodeList.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Object> getDynamicProperties(Object record) {
        Map<String, Object> dictProperties = getDictProperties(record);
        updatePreDefinedProperties(record, dictProperties);
        return dictProperties;
    }


    protected Map<String, Object> getDictProperties(Object record){
        Map<String, Object> properties = new HashMap<>();
        if (record == null){
            return properties;
        }
        List<Field> dictFieldList = this.classDictFieldsMap.get(record.getClass());
        try {
            for (Field field : dictFieldList) {
                field.setAccessible(true);
                String value = CommonUtil.toString(field.get(record));
                if (value == null || "".equals(value)) {
                    continue;
                }

                String dictCode = field.getAnnotation(Dict.class).value();

                List<String> result = new ArrayList<>();
                String[] values = value.split(DictHelperImpl.DICT_VALUES_DELIMITER);
                for (String s : values) {
                    result.add(String.valueOf(getDictValue(dictCode, s)));
                }

                properties.put(field.getName() + DictHelperImpl.DICT_SUFFIX, String.join(DictHelperImpl.DICT_RESULTS_DELIMITER, result));
            }
        } catch (IllegalAccessException e) {
            throw new IllegalAccessRunTimeException(e.getMessage(), e);
        }

        return properties;
    }

    protected Object getDictValue(String dictCode, String key) {
        Map<String, Object> dictData = this.dictDataMap.get(dictCode);
        if (dictData == null){
            throw new DictNotFoundException("dict not found:" + dictCode);
        }
        Object result = dictData.get(key);
        if (result == null){
            throw new DictValueNotFoundException("value not found in dict \"" + dictCode + "\":" + key);
        }
        return result;
    }

    public void replaceProperties(Map<String, Object> objectData, Class<?> clazz) {
        List<Field> dictFieldList = this.classDictFieldsMap.get(clazz);
        for (Field field : dictFieldList) {
            field.setAccessible(true);
            String column = field.getName();
            String dictCode = field.getAnnotation(Dict.class).value();
            String value = CommonUtil.toString(objectData.get(column));
            Object result;
            try {
                result = getDictValue(dictCode, value);
            }catch (DictValueNotFoundException e){
                if (this.dictDataMap.get(dictCode).containsValue(value)){
                    continue;
                }else {
                    throw e;
                }
            }
            objectData.put(column, result);
        }
    }



    public Map<Class<?>, List<Field>> getClassDictFieldsMap() {
        return classDictFieldsMap;
    }

    public void setClassDictFieldsMap(Map<Class<?>, List<Field>> classDictFieldsMap) {
        this.classDictFieldsMap = classDictFieldsMap;
    }

    public List<String> getDictCodeList() {
        return dictCodeList;
    }

    public void setDictCodeList(List<String> dictCodeList) {
        this.dictCodeList = dictCodeList;
    }

    public Map<String, Map<String, Object>> getDictDataMap() {
        return dictDataMap;
    }

    public void setDictDataMap(Map<String, Map<String, Object>> dictDataMap) {
        this.dictDataMap = dictDataMap;
    }
}
