package com.github.Lisim2023.quotes.dict;

import com.github.Lisim2023.quotes.base.PredefinedPropertiesObjectAccessor;

public class DefaultDictHelper extends DictHelperImpl<Object> {

    public DefaultDictHelper(DictDataProvider dictDataProvider){
        this.dynamicObjectAccessor = new PredefinedPropertiesObjectAccessor();
        this.dictDataProvider = dictDataProvider;
    }
}
