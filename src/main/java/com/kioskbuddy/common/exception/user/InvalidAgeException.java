package com.kioskbuddy.common.exception.user;

import com.kioskbuddy.common.exception.BadRequestException;
import com.kioskbuddy.common.exception.ErrorCode;

public class InvalidAgeException extends BadRequestException {

    public InvalidAgeException() {
        super(ErrorCode.INVALID_AGE.getMessage());
    }

    @Override
    public int getCode() {
        return ErrorCode.INVALID_AGE.getCode();
    }
}
