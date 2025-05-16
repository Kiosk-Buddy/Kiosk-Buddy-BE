package com.kioskbuddy.backend.progress.application;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import com.kioskbuddy.backend.progress.application.dto.ProgressCreateRequest;
import com.kioskbuddy.backend.progress.application.dto.ProgressUpdateRequest;
import com.kioskbuddy.backend.progress.domain.Progress;
import com.kioskbuddy.backend.progress.repository.JpaProgressRepository;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import com.kioskbuddy.backend.tutorial.repository.JpaTutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgressService {

    private final JpaProgressRepository progressRepository;
    private final JpaMemberRepository memberRepository;
    private final JpaTutorialRepository tutorialRepository;

    @Transactional
    public Long createProgress(ProgressCreateRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Tutorial tutorial = tutorialRepository.findById(request.tutorialId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 튜토리얼입니다."));

        Progress progress = Progress.create(member, tutorial, request.progressPercentage());

        return progressRepository.save(progress).getId();
    }

    public Progress getProgress(Long progressId) {
        return progressRepository.findWithMemberAndTutorialById(progressId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 진행도입니다."));
    }

    @Transactional
    public void updateProgress(Long progressId, ProgressUpdateRequest request) {
        Progress progress = progressRepository.findWithMemberAndTutorialById(progressId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 진행도입니다."));

        progress.update(request.progressPercentage());
    }

    @Transactional
    public void deleteProgress(Long progressId) {
        Progress progress = progressRepository.findById(progressId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 진행도입니다."));

        progressRepository.delete(progress);
    }
}
