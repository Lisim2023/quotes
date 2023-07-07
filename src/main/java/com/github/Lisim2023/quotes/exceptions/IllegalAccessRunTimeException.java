package com.github.Lisim2023.quotes.exceptions;

public class IllegalAccessRunTimeException extends QuotesException {

    public IllegalAccessRunTimeException() {
        super();
    }

    public IllegalAccessRunTimeException(String message) {
        super(message);
    }

    public IllegalAccessRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAccessRunTimeException(Throwable cause){
        super(cause);
    }
}
