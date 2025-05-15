package com.kioskbuddy.backend.progress.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public record ProgressCreateRequest(
        Long memberId,
        Long tutorialId,

        @DecimalMin(value = "0", message = "진행도는 최소 0이어야 합니다.")
        @DecimalMax(value = "100", message = "진행도는 최대 100이어야 합니다.")
        Float progressPercentage
) {
}
