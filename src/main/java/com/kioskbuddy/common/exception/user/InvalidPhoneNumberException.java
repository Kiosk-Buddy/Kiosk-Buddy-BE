package com.kioskbuddy.common.exception.user;

import com.kioskbuddy.common.exception.BadRequestException;
import com.kioskbuddy.common.exception.ErrorCode;

public class InvalidPhoneNumberException extends BadRequestException {

    public InvalidPhoneNumberException() {
        super(ErrorCode.INVALID_PHONE_NUMBER.getMessage());
    }

    @Override
    public int getCode() {
        return ErrorCode.INVALID_PHONE_NUMBER.getCode();
    }
}
