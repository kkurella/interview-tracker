package com.iview.exception;

public class IviewException extends Throwable {
    private String message;

    public IviewException(String exMsg) {
        this.message = exMsg;
    }
}
