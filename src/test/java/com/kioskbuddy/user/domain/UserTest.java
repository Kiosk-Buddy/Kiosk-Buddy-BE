package com.kioskbuddy.user.domain;

import com.kioskbuddy.common.exception.user.InvalidPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("유저 객체 정상적으로 생성.")
    void createUser_Success() {
        // given
        Long userId = 1L;
        UserInfo userInfo = new UserInfo(65, UserType.SENIOR, "010-1234-5678");
        String validPassword = "Abc12345";

        // when
        User user = User.createUser(userId, userInfo, validPassword);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getUserId()).isEqualTo(userId);
        assertThat(user.getUserInfo()).isEqualTo(userInfo);
        assertThat(user.getPassword()).isEqualTo(validPassword);
    }

    @Test
    @DisplayName("userInfo가 null이면 예외 발생")
    void createUser_NullUserInfo_ShouldThrowException() {
        // when & then
        assertThatThrownBy(() -> User.createUser(1L, null, "Abc12345"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저 정보는 필수 입력 항목입니다.");
    }

    @Test
    @DisplayName("비밀번호가 null이면 예외 발생")
    void createUser_NullPassword_ShouldThrowException() {
        // given
        UserInfo userInfo = new UserInfo(65, UserType.SENIOR, "010-1234-5678");

        // when & then
        assertThatThrownBy(() -> User.createUser(1L, userInfo, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 필수 입력 항목입니다.");
    }

    @Test
    @DisplayName("비밀번호가 8자 미만이면 예외 발생")
    void createUser_ShortPassword_ShouldThrowInvalidPasswordException() {
        // given
        UserInfo userInfo = new UserInfo(65, UserType.SENIOR, "010-1234-5678");

        // when & then
        assertThatThrownBy(() -> User.createUser(1L, userInfo, "A1b2c3"))
                .isInstanceOf(InvalidPasswordException.class);
    }

    @Test
    @DisplayName("비밀번호가 영문과 숫자를 포함하지 않으면 예외 발생")
    void createUser_InvalidPasswordFormat_ShouldThrowInvalidPasswordException() {
        // given
        UserInfo userInfo = new UserInfo(65, UserType.SENIOR, "010-1234-5678");

        // when & then
        assertThatThrownBy(() -> User.createUser(1L, userInfo, "abcdefgh"))
                .isInstanceOf(InvalidPasswordException.class);

        assertThatThrownBy(() -> User.createUser(1L, userInfo, "12345678"))
                .isInstanceOf(InvalidPasswordException.class);
    }

    @Test
    @DisplayName("같은 userId를 가진 User 객체는 equals()가 true를 반환")
    void user_Equals_ShouldReturnTrueForSameUserId() {
        // given
        UserInfo userInfo1 = new UserInfo(65, UserType.SENIOR, "010-1234-5678");
        UserInfo userInfo2 = new UserInfo(65, UserType.SENIOR, "010-1234-5678");

        User user1 = User.createUser(1L, userInfo1, "Abc12345");
        User user2 = User.createUser(1L, userInfo2, "Xyz98765");

        // when & then
        assertThat(user1).isEqualTo(user2);
        assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
    }

    @Test
    @DisplayName("다른 userId를 가진 User 객체는 equals()가 false를 반환")
    void user_Equals_ShouldReturnFalseForDifferentUserId() {
        // given
        UserInfo userInfo1 = new UserInfo(65, UserType.SENIOR, "010-1234-5678");
        UserInfo userInfo2 = new UserInfo(65, UserType.SENIOR, "010-1234-5678");

        User user1 = User.createUser(1L, userInfo1, "Abc12345");
        User user2 = User.createUser(2L, userInfo2, "Xyz98765");

        // when & then
        assertThat(user1).isNotEqualTo(user2);
    }
}
