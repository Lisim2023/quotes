package io.github.lisim2023.quotes.exceptions;

public class SerializationException extends QuotesException {

    public SerializationException() {
        super();
    }

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializationException(Throwable cause){
        super(cause);
    }


}
