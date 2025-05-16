package com.kioskbuddy.backend.simulationlog.application;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import com.kioskbuddy.backend.simulationlog.application.dto.SimulationLogCreateRequest;
import com.kioskbuddy.backend.simulationlog.domain.SimulationLog;
import com.kioskbuddy.backend.simulationlog.repository.jpa.JpaSimulationLogRepository;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import com.kioskbuddy.backend.tutorial.repository.JpaTutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SimulationLogService {

    private final JpaSimulationLogRepository simulationLogRepository;
    private final JpaMemberRepository memberRepository;
    private final JpaTutorialRepository tutorialRepository;

    @Transactional
    public Long createSimulationLog(SimulationLogCreateRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Tutorial tutorial = tutorialRepository.findById(request.tutorialId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 튜토리얼입니다."));

        SimulationLog simulationLog = SimulationLog.create(member, tutorial, request.isSuccess());

        return simulationLogRepository.save(simulationLog).getId();
    }

    public SimulationLog getSimulationLog(Long simulationLogId) {
        return simulationLogRepository.findWithMemberAndTutorialById(simulationLogId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시뮬레이션 기록입니다."));
    }
}
