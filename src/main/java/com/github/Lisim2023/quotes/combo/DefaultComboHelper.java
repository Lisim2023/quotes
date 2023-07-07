package com.github.Lisim2023.quotes.combo;


import com.github.Lisim2023.quotes.base.PredefinedPropertiesObjectAccessor;
import com.github.Lisim2023.quotes.dict.DefaultDictHelper;
import com.github.Lisim2023.quotes.dict.DictDataProvider;
import com.github.Lisim2023.quotes.dict.DictHelper;
import com.github.Lisim2023.quotes.quote.QuoteHelper;
import com.github.Lisim2023.quotes.quote.DefaultQuoteHelper;
import com.github.Lisim2023.quotes.quote.QuoteDataProvider;


public class DefaultComboHelper extends ComboHelperImpl<Object> {

    public DefaultComboHelper(DictHelper dictHelper, QuoteHelper quoteHelper){
        this.dynamicObjectAccessor = new PredefinedPropertiesObjectAccessor();
        this.dictHelper = dictHelper;
        this.quoteHelper = quoteHelper;
    }

    public DefaultComboHelper(DictDataProvider dictDataProvider, QuoteDataProvider quoteDataProvider){
        this.dynamicObjectAccessor = new PredefinedPropertiesObjectAccessor();
        this.dictHelper = new DefaultDictHelper(dictDataProvider);
        this.quoteHelper = new DefaultQuoteHelper(quoteDataProvider);
    }
}
