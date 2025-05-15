package com.kioskbuddy.backend.testsession.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record TestSessionCreateRequest(

        Long memberId,
        Long tutorialId,

        @Min(value = 0, message = "진행도는 최소 0이어야 합니다.")
        @Max(value = 100, message = "진행도는 최대 100이어야 합니다.")
        Integer score
) {
}
