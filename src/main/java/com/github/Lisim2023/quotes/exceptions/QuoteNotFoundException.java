package com.github.Lisim2023.quotes.exceptions;

public class QuoteNotFoundException extends QuotesException {

    public QuoteNotFoundException() {
        super();
    }

    public QuoteNotFoundException(String message) {
        super(message);
    }

    public QuoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuoteNotFoundException(Throwable cause) {
        super(cause);
    }
}
