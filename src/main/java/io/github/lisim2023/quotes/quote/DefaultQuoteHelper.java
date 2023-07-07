package io.github.lisim2023.quotes.quote;

import io.github.lisim2023.quotes.base.PredefinedPropertiesObjectAccessor;

public class DefaultQuoteHelper extends QuoteHelperImpl<Object> {

    public DefaultQuoteHelper(QuoteDataProvider quoteDataProvider){
        this.dynamicObjectAccessor = new PredefinedPropertiesObjectAccessor();
        this.quoteDataProvider = quoteDataProvider;
    }
}
