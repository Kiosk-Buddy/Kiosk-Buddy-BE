package com.kioskbuddy.backend.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    
    @Test
    @DisplayName("Member 생성 테스트")
    void createMember() {
        // given
        String name = "홍길동";
        Integer age = 30;
        String phoneNumber = "010-1234-5678";
        String password = "Abc123!@#";

        // when
        Member member = Member.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .password(password)
                .build();

        // then
        assertEquals(name, member.getName());
        assertEquals(age, member.getAge());
        assertEquals(phoneNumber, member.getPhoneNumber());
        assertEquals(password, member.getPassword());
    }
}