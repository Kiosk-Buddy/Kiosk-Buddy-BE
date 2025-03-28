package com.kioskbuddy.common.util;

import com.kioskbuddy.common.exception.user.InvalidPasswordException;

public class PasswordValidator {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    public static void validate(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수 입력 항목입니다.");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new InvalidPasswordException();
        }
    }
}
