package io.github.lisim2023.quotes.exceptions;

public class QuotesException extends RuntimeException{

    public QuotesException() {
        super();
    }

    public QuotesException(String message) {
        super(message);
    }

    public QuotesException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuotesException(Throwable cause) {
        super(cause);
    }

    public QuotesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
