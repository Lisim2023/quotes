package com.github.Lisim2023.quotes.quote;

import com.github.Lisim2023.quotes.base.PredefinedPropertiesObjectAccessor;

public class DefaultQuoteHelper extends QuoteHelperImpl<Object> {

    public DefaultQuoteHelper(QuoteDataProvider quoteDataProvider){
        this.dynamicObjectAccessor = new PredefinedPropertiesObjectAccessor();
        this.quoteDataProvider = quoteDataProvider;
    }
}
