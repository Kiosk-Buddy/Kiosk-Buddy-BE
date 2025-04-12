package com.kioskbuddy.common.util;

public class PasswordValidator {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    public static void validate(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수 입력 항목입니다.");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상이며, 영문과 숫자를 포함해야 합니다.");
        }
    }
}
