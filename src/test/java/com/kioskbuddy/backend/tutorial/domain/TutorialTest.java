package com.kioskbuddy.backend.tutorial.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TutorialTest {

    @Test
    @DisplayName("Tutorial 생성 테스트")
    void createTutorialTest() {
        // given
        String title = "테스트 튜토리얼";
        String description = "이 튜토리얼은 테스트용입니다.";
        DifficultyLevel difficultyLevel = DifficultyLevel.EASY;

        // when
        Tutorial tutorial = Tutorial.create(title, description, difficultyLevel);
        
        // then
        assertEquals(title, tutorial.getTitle());
        assertEquals(description, tutorial.getDescription());
        assertEquals(difficultyLevel, tutorial.getDifficultyLevel());
    }
}
