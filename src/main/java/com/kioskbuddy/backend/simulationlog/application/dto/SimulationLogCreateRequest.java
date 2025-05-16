package com.kioskbuddy.backend.simulationlog.application.dto;

public record SimulationLogCreateRequest(

        Long memberId,
        Long tutorialId,
        Boolean isSuccess
) {
}
