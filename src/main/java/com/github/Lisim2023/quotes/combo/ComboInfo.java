package com.github.Lisim2023.quotes.combo;


import com.github.Lisim2023.quotes.dict.DictInfo;
import com.github.Lisim2023.quotes.quote.TableData;
import com.github.Lisim2023.quotes.quote.QuoteInfo;

import java.lang.reflect.Field;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * 引用信息与字典信息的组合
 *
 * @author Lisim
 */
public class ComboInfo extends DictInfo {

    private final QuoteInfo quoteInfo = new QuoteInfo();

    public ComboInfo(){
    }

    @Override
    public void analyse(Object record) {
        quoteInfo.analyse(record);
        this.setClassAllFieldsMap(quoteInfo.getClassAllFieldsMap());
        this.setClassRecursionFieldsMap(quoteInfo.getClassRecursionFieldsMap());
        this.analyse();
    }



    @Override
    public Map<String, Object> getDynamicProperties(Object record) {
        Map<String, Object> properties = new HashMap<>();
        properties.putAll(quoteInfo.getDynamicProperties(record));
        properties.putAll(super.getDynamicProperties(record));
        return properties;
    }





    public QuoteInfo getQuoteInfo() { return quoteInfo; }



    public Map<Class<?>, List<Field>> getClassQuoteFieldsMap() {
        return quoteInfo.getClassQuoteFieldsMap();
    }

    public void setClassQuoteFieldsMap(Map<Class<?>, List<Field>> classQuoteFieldsMap) {
        quoteInfo.setClassQuoteFieldsMap(classQuoteFieldsMap);
    }

    public Map<String, TableData> getQuoteDataMap() {
        return quoteInfo.getQuoteDataMap();
    }

    public void setQuoteDataMap(Map<String, TableData> quoteDataMap) {
        quoteInfo.setQuoteDataMap(quoteDataMap);
    }

}
