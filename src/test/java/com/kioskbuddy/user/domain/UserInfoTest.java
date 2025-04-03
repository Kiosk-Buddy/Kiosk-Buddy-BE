package com.kioskbuddy.user.domain;

import com.kioskbuddy.common.exception.user.InvalidAgeException;
import com.kioskbuddy.common.exception.user.InvalidPhoneNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserInfoTest {

    @DisplayName("필수 입력값이 null이면 NullPointerException 발생.")
    @Test
    void createUserInfo_WithNullValues_ShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UserInfo(null, UserType.ADULT, "010-1234-5678"));
        assertThrows(NullPointerException.class, () -> new UserInfo(25, null, "010-1234-5678"));
        assertThrows(NullPointerException.class, () -> new UserInfo(25, UserType.ADULT, null));
    }

    @DisplayName("유효한 입력값으로 UserInfo를 생성하여 성공.")
    @Test
    void createUserInfo_WithValidInput_ShouldCreateSuccessfully() {
        Integer age = 64;
        UserType userType = UserType.SENIOR;
        String phoneNumber = "010-1234-5678";

        UserInfo userInfo = new UserInfo(age, userType, phoneNumber);

        assertEquals(age, userInfo.getAge());
        assertEquals(userType, userInfo.getUserType());
        assertEquals(phoneNumber, userInfo.getPhoneNumber());
    }

    @DisplayName("유효 범위를 벗어난 나이를 입력하여 InvalidAgeException 발생.")
    @ParameterizedTest
    @CsvSource({
            "-1, ADULT, 010-1234-5678",
            "0, ADULT, 010-1234-5678",
            "100, ADULT, 010-1234-5678",
            "101, ADULT, 010-1234-5678"
    })
    void createUserInfo_WithInvalidAge_ShouldThrowInvalidAgeException(Integer age, UserType userType, String phoneNumber) {
        assertThrows(InvalidAgeException.class, () -> new UserInfo(age, userType, phoneNumber));
    }

    @DisplayName("잘못된 전화번호 형식으로 UserInfo를 생성하여 InvalidPhoneNumberException 발생.")
    @ParameterizedTest
    @CsvSource({
//            "25, ADULT, 01012345678",
//            "25, ADULT, 011-1234-5678",
            "25, ADULT, 010-12-5678",
            "25, ADULT, 010-1234-567"
    })
    void createUserInfo_WithInvalidPhoneNumber_ShouldThrowInvalidPhoneNumberException(Integer age, UserType userType, String phoneNumber) {
        assertThrows(InvalidPhoneNumberException.class, () -> new UserInfo(age, userType, phoneNumber));
    }
}
