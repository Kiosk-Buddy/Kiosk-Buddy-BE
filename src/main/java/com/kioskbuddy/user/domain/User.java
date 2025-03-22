package com.kioskbuddy.user.domain;

import com.kioskbuddy.common.exception.user.InvalidPasswordException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long userId;
    private UserInfo userInfo;
    private String password;

    private void validateUserId(Long userId) {
        if (userId == null) {
            throw new NullPointerException();
        }
    }

    private void validateUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            throw new NullPointerException();
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new NullPointerException("비밀번호는 필수 입력 항목입니다.");
        }
        if (password.length() < 8) {
            throw new InvalidPasswordException();
        }
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            throw new InvalidPasswordException();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        User user = (User) obj;
        return Objects.equals(userId, user.userId);
    }
}
