package com.github.Lisim2023.quotes.quote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <P>
 *   引用数据封装类
 * </P>
 *
 * @author Lisim
 */
public class TableData {

    private String table;
    private String key;
    private List<String> valueList;

    private Map<String, Map<String, Object>> resultMap;


    public TableData(String table, String key){
        this.table = table;
        this.key = key;
        this.valueList = new ArrayList<>();
        this.resultMap = new HashMap<>();
    }

    public TableData(String table, String key, List<String> values){
        this(table, key);
        this.valueList = values;
    }

    public void putQuoteData(String value, Map<String, Object> quoteData){
        this.resultMap.put(value, quoteData);
    }

    public Map<String, Object> getQuoteData(String value){
        return this.resultMap.get(value);
    }



    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public Map<String, Map<String, Object>> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Map<String, Object>> resultMap) {
        this.resultMap = resultMap;
    }
}
