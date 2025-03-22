package com.kioskbuddy.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Client
    INVALID_AGE(422, "나이는 1 이상 99 이하로 입력해야 합니다."),
    INVALID_PHONE_NUMBER(422, "전화번호 형식이 알맞지 않습니다."),
    INVALID_PASSWORD(400, "비밀번호는 최소 8자 이상이며, 영문과 숫자를 포함해야 합니다."),

    // Server
    INTERNAL_SEVER_ERROR(500, "서버 에러가 발생했습니다. 관리자에게 문의 바랍니다.");

    private final Integer code;
    private final String message;
}
