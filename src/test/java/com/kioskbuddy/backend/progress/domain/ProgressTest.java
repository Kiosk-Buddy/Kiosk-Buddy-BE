package com.kioskbuddy.backend.progress.domain;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.domain.MemberType;
import com.kioskbuddy.backend.tutorial.domain.DifficultyLevel;
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
        Member member = Member.create("홍길동", 30, "010-1234-5678", "Abc123!@#", MemberType.SENIOR);

        Tutorial tutorial = Tutorial.create("테스트 튜토리얼", "테스트 설명", DifficultyLevel.EASY);

        Float progressPercentage = 45.5f;

        // when
        Progress progress = Progress.create(member, tutorial, progressPercentage);

        // then
        assertEquals(member, progress.getMember());
        assertEquals(tutorial, progress.getTutorial());
        assertEquals(progressPercentage, progress.getProgressPercentage());
    }
}
