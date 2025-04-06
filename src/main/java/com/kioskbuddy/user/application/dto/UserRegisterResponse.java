package com.kioskbuddy.user.application.dto;

import com.kioskbuddy.user.domain.User;

public record UserRegisterResponse(Long userId, String userType, Integer age, String phoneNumber) {

    public UserRegisterResponse(User user) {
        this(
                user.getUserId(),
                user.getUserInfo().getUserType().name(),
                user.getUserInfo().getAge(),
                user.getPassword()
        );
    }
}
