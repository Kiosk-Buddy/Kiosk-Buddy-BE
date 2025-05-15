package com.kioskbuddy.backend.progress.application;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import com.kioskbuddy.backend.progress.application.dto.ProgressCreateRequest;
import com.kioskbuddy.backend.progress.application.dto.ProgressUpdateRequest;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void createProgressTest() {
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
    @Test
    @DisplayName("진행도 조회 성공")
    void getProgressTest() {
        // given
        Member member = Member.create("홍길동", 30, "010-1111-2222", "pw");
        Tutorial tutorial = Tutorial.create("타이틀", "설명", DifficultyLevel.EASY);
        Progress progress = Progress.create(member, tutorial, 0.6f);
        ReflectionTestUtils.setField(member, "id", 1L);
        ReflectionTestUtils.setField(tutorial, "id", 2L);
        ReflectionTestUtils.setField(progress, "id", 3L);

        given(progressRepository.findWithMemberAndTutorialById(3L)).willReturn(Optional.of(progress));

        // when
        Progress result = progressService.getProgress(3L);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getProgressPercentage()).isEqualTo(0.6f);
        assertThat(result.getMember().getId()).isEqualTo(1L);
        assertThat(result.getTutorial().getId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("진행도 업데이트 성공")
    void updateProgressTest() {
        // given
        Member member = Member.create("홍길동", 30, "010-3333-4444", "pw");
        Tutorial tutorial = Tutorial.create("튜토", "설명", DifficultyLevel.HARD);
        Progress progress = Progress.create(member, tutorial, 0.3f);
        ReflectionTestUtils.setField(progress, "id", 5L);

        given(progressRepository.findWithMemberAndTutorialById(5L)).willReturn(Optional.of(progress));

        ProgressUpdateRequest request = new ProgressUpdateRequest(0.9f);

        // when
        progressService.updateProgress(5L, request);

        // then
        assertThat(progress.getProgressPercentage()).isEqualTo(0.9f);
    }

    @Test
    @DisplayName("존재하지 않는 진행도 조회 시 예외 발생")
    void getProgressThrowsExceptionWhenNotFound() {
        // given
        given(progressRepository.findWithMemberAndTutorialById(999L)).willReturn(Optional.empty());

        // expect
        assertThatThrownBy(() -> progressService.getProgress(999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 진행도입니다.");
    }

    @Test
    @DisplayName("존재하지 않는 진행도 업데이트 시 예외 발생")
    void updateProgressThrowsExceptionWhenNotFound() {
        // given
        given(progressRepository.findWithMemberAndTutorialById(999L)).willReturn(Optional.empty());
        ProgressUpdateRequest request = new ProgressUpdateRequest(0.7f);

        // expect
        assertThatThrownBy(() -> progressService.updateProgress(999L, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 진행도입니다.");
    }

    @Test
    @DisplayName("진행도 정상 삭제")
    void deleteProgressTest() {
        // given
        Member member = Member.create("홍길동", 40, "010-1234-5678", "password");
        Tutorial tutorial = Tutorial.create("튜토리얼 제목", "설명", DifficultyLevel.MEDIUM);

        ReflectionTestUtils.setField(member, "id", 1L);
        ReflectionTestUtils.setField(tutorial, "id", 1L);

        Progress progress = Progress.create(member, tutorial, 0.5f);
        ReflectionTestUtils.setField(progress, "id", 100L);

        given(progressRepository.findById(100L)).willReturn(Optional.of(progress));

        // when
        progressService.deleteProgress(100L);

        // then
        verify(progressRepository).delete(progress);
    }
}