package com.kioskbuddy.common.ui;

import com.kioskbuddy.common.exception.BadRequestException;
import com.kioskbuddy.common.exception.ErrorCode;
import com.kioskbuddy.common.exception.InternalServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleBadRequestException(BadRequestException e) {
        log.warn(e.getMessage(), e);

        return new ErrorDetails(e);
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleInternalServerException(InternalServerException e) {
        log.error(e.getMessage(), e);

        return new ErrorDetails(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleException(Exception e) {
        log.error(e.getMessage(), e);

        return new ErrorDetails(ErrorCode.INTERNAL_SEVER_ERROR);
    }
}
