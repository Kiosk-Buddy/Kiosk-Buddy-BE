package com.kioskbuddy.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SEVER_ERROR(500, "서버 에러가 발생했습니다. 관리자에게 문의 바랍니다.");

    private final Integer code;
    private final String message;
}
