package com.github.Lisim2023.quotes.exceptions;

public class DynamicObjectAccessException extends QuotesException {

    public DynamicObjectAccessException() {
        super();
    }

    public DynamicObjectAccessException(String message) {
        super(message);
    }

    public DynamicObjectAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DynamicObjectAccessException(Throwable cause){
        super(cause);
    }
}
