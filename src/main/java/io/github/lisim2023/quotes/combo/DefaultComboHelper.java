package io.github.lisim2023.quotes.combo;


import io.github.lisim2023.quotes.base.PredefinedPropertiesObjectAccessor;
import io.github.lisim2023.quotes.dict.DefaultDictHelper;
import io.github.lisim2023.quotes.dict.DictDataProvider;
import io.github.lisim2023.quotes.dict.DictHelper;
import io.github.lisim2023.quotes.quote.QuoteHelper;
import io.github.lisim2023.quotes.quote.DefaultQuoteHelper;
import io.github.lisim2023.quotes.quote.QuoteDataProvider;


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
