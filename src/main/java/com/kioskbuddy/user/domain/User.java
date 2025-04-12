package com.kioskbuddy.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kioskbuddy.common.util.PasswordValidator;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Embedded // UserInfo가 Embeddable이라면 사용
    private UserInfo userInfo;

    @JsonIgnore
    private String password;

    @Builder
    public User(Long userId, UserInfo userInfo, String password) {
        if (userInfo == null) {
            throw new IllegalArgumentException("유저 정보는 필수 입력 항목입니다.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수 입력 항목입니다.");
        }

        this.userId = userId;
        this.userInfo = userInfo;
        this.password = password;
    }

    public static User createUser(Long userId, UserInfo userInfo, String password) {
        return new User(userId, userInfo, password);
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