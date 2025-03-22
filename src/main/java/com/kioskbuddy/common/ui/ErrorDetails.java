package com.kioskbuddy.common.ui;

import com.kioskbuddy.common.exception.BadRequestException;
import com.kioskbuddy.common.exception.ErrorCode;
import com.kioskbuddy.common.exception.InternalServerException;

public record ErrorDetails(int errorCode, String message) {

    public <T extends BadRequestException> ErrorDetails(T e) {
        this(e.getCode(), e.getMessage());
    }

    public <T extends InternalServerException> ErrorDetails(T e) {
        this(e.getCode(), e.getMessage());
    }

    public ErrorDetails(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }
}
