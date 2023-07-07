package io.github.lisim2023.quotes.quote;

import io.github.lisim2023.quotes.exceptions.IllegalAccessRunTimeException;
import io.github.lisim2023.quotes.exceptions.QuoteNotFoundException;
import io.github.lisim2023.quotes.base.AbstractStructure;
import io.github.lisim2023.quotes.common.CommonUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * <P>
 *   引用任务相关信息
 *   1.收集引用任务中涉及到的数据信息, 以便从缓存及数据库批量查询相关数据.
 *   2.将查询到的数据保存到此对象, 之后在任务过程中由此对象提供数据.
 * </P>
 *
 * @author Lisim
 */
public class QuoteInfo extends AbstractStructure {

    protected Map<Class<?>, List<Field>> classQuoteFieldsMap = new HashMap<>();
    protected Map<String, TableData> quoteDataMap = new HashMap<>();

    public QuoteInfo(){

    }

    public QuoteInfo(Object record){
        this.analyse(record);
    }



    @Override
    public void analyse(Object record) {
        super.analyse(record);
        this.classQuoteFieldsMap = collectAnnotationFields(Quote.class);
        collectQuoteValues(record);
    }





    protected void collectQuoteValues(Collection<?> records) {
        if (records == null) {
            return;
        }
        for (Object record : records) {
            collectQuoteValues(record);
        }
    }

    protected void collectQuoteValues(Map<?, ?> recordMap){
        if (recordMap == null){
            return;
        }
        for (Object value : recordMap.values()) {
            collectQuoteValues(value);
        }
    }

    protected void collectQuoteValues(Object record) {
        if (record == null) {
            return;
        }
        if (record instanceof Collection){
            collectQuoteValues((Collection<?>) record);
            return;
        }
        if (record instanceof Map){
            collectQuoteValues((Map<?, ?>) record);
            return;
        }
        Class<?> clazz = record.getClass();
        List<Field> quoteFieldList = this.classQuoteFieldsMap.get(clazz);
        if (quoteFieldList == null) {
            return;
        }
        try {
            for (Field field : quoteFieldList) {
                field.setAccessible(true);
                Object value = field.get(record);
                if (value == null || "".equals(value)){
                    continue;
                }
                List<String> values = Arrays.asList(CommonUtil.toString(value).split(QuoteHelperImpl.QUOTE_VALUES_DELIMITER));
                Quote quote = field.getAnnotation(Quote.class);
                TableData tableData = getOrCreateTableData(quote.table(), quote.key());
                tableData.getValueList().removeAll(values);
                tableData.getValueList().addAll(values);
            }
            List<Field> recursionFieldList = this.classRecursionFieldsMap.get(clazz);
            if (recursionFieldList == null){
                return;
            }
            for (Field field : recursionFieldList) {
                field.setAccessible(true);
                Object recursionObj = field.get(record);
                collectQuoteValues(recursionObj);
            }
        }catch (IllegalAccessException e){
            throw new IllegalAccessRunTimeException(e.getMessage(), e);
        }
    }





    @Override
    public Map<String, Object> getDynamicProperties(Object record) {
        Map<String, Object> quoteProperties = getQuoteProperties(record);
        updatePreDefinedProperties(record, quoteProperties);
        return quoteProperties;
    }


    protected Map<String, Object> getQuoteProperties(Object record){
        Map<String, Object> properties = new HashMap<>();
        List<Field> quoteFieldList = this.classQuoteFieldsMap.get(record.getClass());

        try{
            for (Field field : quoteFieldList) {
                field.setAccessible(true);
                String value = CommonUtil.toString(field.get(record));
                if (value == null || "".equals(value)) {
                    continue;
                }

                Quote quote = field.getAnnotation(Quote.class);
                String fieldName = field.getName();
                String[] columns = quote.columns();
                String table = quote.table();
                String key = quote.key();

                for (String column : columns) {
                    List<String> result = new ArrayList<>();
                    String[] values = value.split(QuoteHelperImpl.QUOTE_VALUES_DELIMITER);
                    TableData tableData = getTableData(table, key);
                    for (String s : values) {
                        Map<String, Object> quoteData = tableData.getQuoteData(s);
                        if (quoteData == null){
                            throw new QuoteNotFoundException("quote data not found in \"" + table +"\":" + key + "=" + value);
                        }
                        Object columnValue = quoteData.get(column);
                        if (columnValue == null){
                            result.add(QuoteHelperImpl.NULL_STRING);
                        }else {
                            result.add(columnValue.toString());
                        }
                    }
                    properties.put(fieldName + QuoteHelperImpl.QUOTE_INFIX + column, String.join(QuoteHelperImpl.QUOTE_RESULTS_DELIMITER, result));
                }
            }
        }catch (IllegalAccessException e){
            throw new IllegalAccessRunTimeException(e.getMessage(), e);
        }

        return properties;
    }





    public TableData getOrCreateTableData(String table, String key){
        return this.quoteDataMap.computeIfAbsent(table + "_" + key, k -> new TableData(table, key));
    }

    public TableData getTableData(String table, String key){
        return this.quoteDataMap.get(table + "_" + key);
    }

    public void putTableData(String table, String key, TableData tableData){
        this.quoteDataMap.put(table + "_" + key, tableData);
    }




    public Map<Class<?>, List<Field>> getClassQuoteFieldsMap() {
        return classQuoteFieldsMap;
    }

    public void setClassQuoteFieldsMap(Map<Class<?>, List<Field>> classQuoteFieldsMap) {
        this.classQuoteFieldsMap = classQuoteFieldsMap;
    }

    public Map<String, TableData> getQuoteDataMap() {
        return quoteDataMap;
    }

    public void setQuoteDataMap(Map<String, TableData> quoteDataMap) {
        this.quoteDataMap = quoteDataMap;
    }
}
