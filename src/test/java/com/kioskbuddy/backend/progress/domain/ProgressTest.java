package com.kioskbuddy.backend.progress.domain;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProgressTest {

    @Test
    @DisplayName("Progress 생성 테스트")
    void createProgressTest() {
        // given
        String name = "홍길동";
        Integer age = 30;
        String phoneNumber = "010-1234-5678";
        String password = "Abc123!@#";

        Member member = Member.create(name, age, phoneNumber, password);

        Tutorial tutorial = Tutorial.builder()
                .title("키오스크 튜토리얼")
                .description("기초 사용법 안내")
                .build();

        Float progressPercentage = 45.5f;

        // when
        Progress progress = Progress.builder()
                .member(member)
                .tutorial(tutorial)
                .progressPercentage(progressPercentage)
                .build();

        // then
        assertEquals(member, progress.getMember());
        assertEquals(tutorial, progress.getTutorial());
        assertEquals(progressPercentage, progress.getProgressPercentage());
    }
}
