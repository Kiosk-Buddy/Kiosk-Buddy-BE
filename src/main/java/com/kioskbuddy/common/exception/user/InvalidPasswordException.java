package com.kioskbuddy.common.exception.user;

import com.kioskbuddy.common.exception.BadRequestException;
import com.kioskbuddy.common.exception.ErrorCode;

public class InvalidPasswordException extends BadRequestException {

    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD.getMessage());
    }

    @Override
    public int getCode() {
        return ErrorCode.INVALID_PASSWORD.getCode();
    }
}
