package com.github.Lisim2023.quotes.exceptions;

public class DictNotFoundException extends QuotesException {

    public DictNotFoundException(){
        super();
    }

    public DictNotFoundException(String message){
        super(message);
    }

    public DictNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DictNotFoundException(Throwable cause) {
        super(cause);
    }
}
