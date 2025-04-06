package com.kioskbuddy.user.application.dto;

import com.kioskbuddy.user.domain.UserType;

public record UserRegisterRequest(Integer age, String userType, String phoneNumber, String password) {

    public UserType getUserTypeEnum(String userType) {
        return UserType.valueOf(this.userType.toUpperCase());
    }
}
