package com.kioskbuddy.user.domain;

import com.kioskbuddy.common.exception.user.InvalidAgeException;
import com.kioskbuddy.common.exception.user.InvalidPhoneNumberException;
import com.kioskbuddy.common.util.PhoneNumberValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자
public class UserInfo {

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Column(nullable = false, length = 15) // 전화번호 길이 제한 추가
    private String phoneNumber;

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
