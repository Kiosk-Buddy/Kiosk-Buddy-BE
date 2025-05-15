package com.kioskbuddy.backend.testsession.application;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import com.kioskbuddy.backend.testsession.application.dto.TestSessionCreateRequest;
import com.kioskbuddy.backend.testsession.domain.TestSession;
import com.kioskbuddy.backend.testsession.repository.jpa.JpaTestSessionRepository;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import com.kioskbuddy.backend.tutorial.repository.JpaTutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestSessionService {

    private final JpaTestSessionRepository testSessionRepository;
    private final JpaMemberRepository memberRepository;
    private final JpaTutorialRepository tutorialRepository;

    @Transactional
    public Long createTestSession(TestSessionCreateRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Tutorial tutorial = tutorialRepository.findById(request.tutorialId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 튜토리얼입니다."));

        TestSession testSession = TestSession.create(member, tutorial, request.score());

        return testSessionRepository.save(testSession).getId();
    }
}
