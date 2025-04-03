package com.kioskbuddy.user.domain;

import com.kioskbuddy.common.exception.user.InvalidAgeException;
import com.kioskbuddy.common.exception.user.InvalidPhoneNumberException;
import com.kioskbuddy.common.util.PhoneNumberValidator;
import lombok.Getter;

@Getter
public class UserInfo {

    private final Integer age;
    private final UserType userType;
    private final String phoneNumber;

    public UserInfo(Integer age, UserType userType, String phoneNumber) {
        if (age == null || userType == null || phoneNumber == null) {
            throw new NullPointerException("유저 정보는 필수 입력 항목입니다.");
        }

        if (age <= 0 || age >= 100) {
            throw new InvalidAgeException();
        }

        if (!PhoneNumberValidator.isValidPhoneNumber(phoneNumber)) {
            throw new InvalidPhoneNumberException();
        }

        this.age = age;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
    }
}
