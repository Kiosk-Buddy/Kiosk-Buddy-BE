package com.kioskbuddy.common.exception;

public abstract class InternalServerException extends RuntimeException {

    public InternalServerException(String message) {
        super(message);
    }

    public abstract int getCode();
}
