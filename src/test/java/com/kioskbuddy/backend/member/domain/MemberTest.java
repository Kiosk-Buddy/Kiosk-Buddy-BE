package com.kioskbuddy.backend.member.domain;

import com.kioskbuddy.backend.member.application.dto.MemberUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    
    @Test
    @DisplayName("Member 생성 테스트")
    void createMemberTest() {
        // given
        String name = "홍길동";
        Integer age = 30;
        String phoneNumber = "010-1234-5678";
        String password = "Abc123!@#";
        MemberType memberType = MemberType.SENIOR;

        // when
        Member member = Member.create(name, age, phoneNumber, password, memberType);

        // then
        assertEquals(name, member.getName());
        assertEquals(age, member.getAge());
        assertEquals(phoneNumber, member.getPhoneNumber());
        assertEquals(password, member.getPassword());
    }
    
    @Test
    @DisplayName("Member 업데이트 테스트")
    void updateMemberTest() {
        // given
        String name = "홍길동";
        Integer age = 30;
        String phoneNumber = "010-1234-5678";
        String password = "Abc123!@#";
        MemberType memberType = MemberType.SENIOR;

        Member member = Member.create(name, age, phoneNumber, password, memberType);
        
        // when
        String newName = "김철수";
        Integer newAge = 35;
        String newPhoneNumber = "010-9876-5432";
        MemberType newMemberType = MemberType.GENERAL;

        member.update(new MemberUpdateRequest(newName, newAge, newPhoneNumber, newMemberType));
        
        // then
        assertEquals(newName, member.getName());
        assertEquals(newAge, member.getAge());
        assertEquals(newPhoneNumber, member.getPhoneNumber());
    } 
}