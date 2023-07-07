package com.github.Lisim2023.quotes.exceptions;

public class QuoteKeyMissingException extends QuotesException {

    public QuoteKeyMissingException() {
    }

    public QuoteKeyMissingException(String message) {
        super(message);
    }

    public QuoteKeyMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuoteKeyMissingException(Throwable cause) {
        super(cause);
    }

    public QuoteKeyMissingException(String table, String key){
        super("missing key information on " + table + ", needed : "
                + key + " & value of " + key);
    }
}
