package com.kioskbuddy.backend.progress.application;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import com.kioskbuddy.backend.progress.application.dto.ProgressCreateRequest;
import com.kioskbuddy.backend.progress.domain.Progress;
import com.kioskbuddy.backend.progress.repository.JpaProgressRepository;
import com.kioskbuddy.backend.tutorial.domain.DifficultyLevel;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import com.kioskbuddy.backend.tutorial.repository.JpaTutorialRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProgressServiceTest {

    @Mock
    private JpaProgressRepository progressRepository;

    @Mock
    private JpaMemberRepository memberRepository;

    @Mock
    private JpaTutorialRepository tutorialRepository;

    @InjectMocks
    private ProgressService progressService;

    @Test
    @DisplayName("Progress 정상 등록 - ID 반환 확인")
    void createProgress_success() {
        // given
        Member member = Member.create("홍길동", 40, "010-1234-5678", "password");
        Tutorial tutorial = Tutorial.create("튜토리얼 제목", "설명", DifficultyLevel.MEDIUM);

        ReflectionTestUtils.setField(member, "id", 1L);
        ReflectionTestUtils.setField(tutorial, "id", 1L);

        ProgressCreateRequest request = new ProgressCreateRequest(1L, 1L, 75.0f);

        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        given(tutorialRepository.findById(1L)).willReturn(Optional.of(tutorial));

        Progress savedProgress = Progress.create(member, tutorial, 75.0f);
        ReflectionTestUtils.setField(savedProgress, "id", 10L); // 저장된 ID 설정

        given(progressRepository.save(any(Progress.class))).willReturn(savedProgress);

        // when
        Long result = progressService.createProgress(request);

        // then
        assertEquals(10L, result);

        // 추가 검증: save()에 전달된 Progress 객체 확인
        ArgumentCaptor<Progress> captor = ArgumentCaptor.forClass(Progress.class);
        verify(progressRepository).save(captor.capture());

        Progress captured = captor.getValue();
        assertEquals(75.0f, captured.getProgressPercentage());
        assertEquals(member, captured.getMember());
        assertEquals(tutorial, captured.getTutorial());
    }
}