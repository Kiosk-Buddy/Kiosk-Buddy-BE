package com.kioskbuddy.user.application.dto;

import com.kioskbuddy.user.domain.User;

public record UserRegisterResponse(Long userId, String userType, Integer age, String phoneNumber) {

    public static UserRegisterResponse userRegisterResponse(User user) {
        return new UserRegisterResponse(
                user.getUserId(),
                user.getUserInfo().getUserType().getDescription(),
                user.getUserInfo().getAge(),
                user.getUserInfo().getPhoneNumber()
        );
    }
}
