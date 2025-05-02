package com.kioskbuddy.backend.tutorial.application;

import com.kioskbuddy.backend.tutorial.application.dto.TutorialCreateRequest;
import com.kioskbuddy.backend.tutorial.application.dto.TutorialUpdateRequest;
import com.kioskbuddy.backend.tutorial.domain.DifficultyLevel;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import com.kioskbuddy.backend.tutorial.repository.JpaTutorialRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TutorialServiceTest {

    @Mock
    private JpaTutorialRepository tutorialRepository;

    @InjectMocks
    private TutorialService tutorialService;

    @Test
    @DisplayName("Tutorial 등록 테스트")
    void registerTutorialTest() {
        // given
        String title = "테스트 튜토리얼";
        String description = "이 튜토리얼은 테스트용입니다.";
        DifficultyLevel difficultyLevel = DifficultyLevel.EASY;

        TutorialCreateRequest request = new TutorialCreateRequest(title, description, difficultyLevel);
        Long expectedId = 1L;

        Tutorial tutorial = Tutorial.builder()
                .title(title)
                .description(description)
                .difficultyLevel(difficultyLevel)
                .build();

        // id를 설정한 Tutorial 객체를 반환하도록 설정
        Tutorial savedTutorial = Tutorial.builder()
                .title(title)
                .description(description)
                .difficultyLevel(difficultyLevel)
                .build();
        ReflectionTestUtils.setField(savedTutorial, "id", expectedId); // ReflectionTestUtils로 id 설정

        // when
        given(tutorialRepository.save(any(Tutorial.class))).willReturn(savedTutorial);
        Long tutorialId = tutorialService.createTutorial(request);

        // then
        assertEquals(expectedId, tutorialId);
        assertEquals(title, savedTutorial.getTitle());
        assertEquals(description, savedTutorial.getDescription());
        assertEquals(difficultyLevel, savedTutorial.getDifficultyLevel());
        assertEquals(expectedId, savedTutorial.getId());
    }

    @Test
    @DisplayName("Tutorial 조회 테스트")
    void getTutorialTest() {
        // given
        Long tutorialId = 1L;
        String title = "테스트 튜토리얼";
        String description = "이 튜토리얼은 테스트용입니다.";
        DifficultyLevel difficultyLevel = DifficultyLevel.EASY;

        Tutorial tutorial = Tutorial.builder()
                .title(title)
                .description(description)
                .difficultyLevel(difficultyLevel)
                .build();

        ReflectionTestUtils.setField(tutorial, "id", tutorialId);

        // when
        given(tutorialRepository.findById(tutorialId)).willReturn(java.util.Optional.of(tutorial));
        Tutorial foundTutorial = tutorialService.getTutorial(tutorialId);

        // then
        assertEquals(tutorialId, foundTutorial.getId());
        assertEquals(title, foundTutorial.getTitle());
        assertEquals(description, foundTutorial.getDescription());
        assertEquals(difficultyLevel, foundTutorial.getDifficultyLevel());
    }

    @Test
    @DisplayName("Tutorial 수정 테스트")
    void updateTutorialTest() {
        // given
        Long tutorialId = 1L;
        String title = "테스트 튜토리얼";
        String description = "이 튜토리얼은 테스트용입니다.";
        DifficultyLevel difficultyLevel = DifficultyLevel.EASY;

        Tutorial tutorial = Tutorial.builder()
                .title(title)
                .description(description)
                .difficultyLevel(difficultyLevel)
                .build();

        ReflectionTestUtils.setField(tutorial, "id", tutorialId);

        // when
        given(tutorialRepository.findById(tutorialId)).willReturn(java.util.Optional.of(tutorial));
        tutorialService.updateTutorial(tutorialId, new TutorialUpdateRequest("수정된 제목", "수정된 설명", DifficultyLevel.MEDIUM));

        // then
        assertEquals("수정된 제목", tutorial.getTitle());
        assertEquals("수정된 설명", tutorial.getDescription());
        assertEquals(DifficultyLevel.MEDIUM, tutorial.getDifficultyLevel());
    }
}