package com.kioskbuddy.backend.testsession.application;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import com.kioskbuddy.backend.testsession.application.dto.TestSessionCreateRequest;
import com.kioskbuddy.backend.testsession.domain.TestSession;
import com.kioskbuddy.backend.testsession.repository.jpa.JpaTestSessionRepository;
import com.kioskbuddy.backend.tutorial.domain.DifficultyLevel;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import com.kioskbuddy.backend.tutorial.repository.JpaTutorialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestSessionServiceTest {


    @InjectMocks
    private TestSessionService testSessionService;

    @Mock
    private JpaTestSessionRepository testSessionRepository;

    @Mock
    private JpaMemberRepository memberRepository;

    @Mock
    private JpaTutorialRepository tutorialRepository;

    @Test
    void createTestSessionTest() {
        // given
        Member member = Member.create("홍길동", 40, "010-1234-5678", "password");
        Tutorial tutorial = Tutorial.create("튜토리얼 제목", "설명", DifficultyLevel.MEDIUM);

        ReflectionTestUtils.setField(member, "id", 1L);
        ReflectionTestUtils.setField(tutorial, "id", 1L);

        Integer score = 90;

        TestSession testSession = TestSession.create(member, tutorial, score);
        TestSession savedSession = TestSession.create(member, tutorial, score);
        ReflectionTestUtils.setField(savedSession, "id", 10L); // 저장된 ID 설정

        TestSessionCreateRequest request = new TestSessionCreateRequest(1L, 1L, score);

        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        given(tutorialRepository.findById(1L)).willReturn(Optional.of(tutorial));
        given(testSessionRepository.save(any(TestSession.class))).willReturn(savedSession);

        // when
        Long result = testSessionService.createTestSession(request);

        // then
        assertEquals(10L, result);
        verify(memberRepository).findById(1L);
        verify(tutorialRepository).findById(1L);
        verify(testSessionRepository).save(any(TestSession.class));
    }
}