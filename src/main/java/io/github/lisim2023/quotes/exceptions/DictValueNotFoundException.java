package io.github.lisim2023.quotes.exceptions;

public class DictValueNotFoundException extends QuotesException {

    public DictValueNotFoundException() {
        super();
    }

    public DictValueNotFoundException(String message) {
        super(message);
    }

    public DictValueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DictValueNotFoundException(Throwable cause) {
        super(cause);
    }
}
