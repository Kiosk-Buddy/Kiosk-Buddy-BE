package com.kioskbuddy.backend.simulationlog.application;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import com.kioskbuddy.backend.simulationlog.application.dto.SimulationLogCreateRequest;
import com.kioskbuddy.backend.simulationlog.domain.SimulationLog;
import com.kioskbuddy.backend.simulationlog.repository.jpa.JpaSimulationLogRepository;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SimulationLogServiceTest {

    @InjectMocks
    private SimulationLogService simulationLogService;

    @Mock
    private JpaSimulationLogRepository simulationLogRepository;

    @Mock
    private JpaMemberRepository memberRepository;

    @Mock
    private JpaTutorialRepository tutorialRepository;

    @Test
    @DisplayName("SimulationLog 등록 테스트")
    void createSimulationLogTest() {
        // given
        Long memberId = 1L;
        Long tutorialId = 10L;
        boolean success = true;

        Member member = Member.create("홍길동", 40, "010-1234-5678", "password");
        Tutorial tutorial = Tutorial.create("튜토리얼 제목", "설명", DifficultyLevel.MEDIUM);

        ReflectionTestUtils.setField(member, "id", 1L);
        ReflectionTestUtils.setField(tutorial, "id", 1L);

        SimulationLog simulationLog = SimulationLog.create(member, tutorial, success);
        ReflectionTestUtils.setField(simulationLog, "id", 100L);

        SimulationLogCreateRequest request = new SimulationLogCreateRequest(memberId, tutorialId, success);

        given(memberRepository.findById(memberId)).willReturn(Optional.of(member));
        given(tutorialRepository.findById(tutorialId)).willReturn(Optional.of(tutorial));
        given(simulationLogRepository.save(any(SimulationLog.class))).willReturn(simulationLog);

        // when
        Long result = simulationLogService.createSimulationLog(request);

        // then
        assertEquals(100L, result);
    }
}