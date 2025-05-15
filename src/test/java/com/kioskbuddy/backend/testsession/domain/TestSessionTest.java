package com.kioskbuddy.backend.testsession.domain;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.tutorial.domain.DifficultyLevel;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestSessionTest {
    
    @Test
    @DisplayName("TestSession 생성 테스트")
    void createTestSessionTest() {
        //given
        Member member = Member.create("홍길동", 30, "010-1234-5678", "Abc123!@#");

        Tutorial tutorial = Tutorial.create("테스트 튜토리얼", "테스트 설명", DifficultyLevel.EASY);

        Integer score = 80;

        // when
        TestSession testSession = TestSession.create(member, tutorial, score);
        
        // then
        assertEquals(member, testSession.getMember());
        assertEquals(tutorial, testSession.getTutorial());
        assertEquals(score, testSession.getScore());
    }
}
