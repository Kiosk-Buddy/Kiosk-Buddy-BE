package com.kioskbuddy.backend.simulationlog.domain;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.domain.MemberType;
import com.kioskbuddy.backend.tutorial.domain.DifficultyLevel;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationLogTest {
    
    @Test
    @DisplayName("SimulationLog 생성 테스트")
    void createSimulationLogTest() {
        // given
        Member member = Member.create("홍길동", 30, "010-1234-5678", "Abc123!@#", MemberType.SENIOR);

        Tutorial tutorial = Tutorial.create("테스트 튜토리얼", "테스트 설명", DifficultyLevel.EASY);

        Boolean isSuccess = true;
        
        // when
        SimulationLog simulationLog = SimulationLog.create(member, tutorial, isSuccess);
        
        // then
        assertEquals(member, simulationLog.getMember());
        assertEquals(tutorial, simulationLog.getTutorial());
        assertEquals(isSuccess, simulationLog.getIsSuccess());
    }
}
