package com.kioskbuddy.backend.simulationlog.application.dto;

import com.kioskbuddy.backend.simulationlog.domain.SimulationLog;

public record SimulationLogDetailResponse(

        String memberName,
        String tutorialTitle,
        Boolean isSuccess
) {
    public static SimulationLogDetailResponse from(SimulationLog simulationLog) {
        return new SimulationLogDetailResponse(
                simulationLog.getMember().getName(),
                simulationLog.getTutorial().getTitle(),
                simulationLog.getIsSuccess()
        );
    }
}
