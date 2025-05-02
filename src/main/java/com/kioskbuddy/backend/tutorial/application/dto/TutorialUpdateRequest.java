package com.kioskbuddy.backend.tutorial.application.dto;

import com.kioskbuddy.backend.tutorial.domain.DifficultyLevel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record TutorialUpdateRequest(

        @NotEmpty(message = "제목은 필수입니다.")
        @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
        String title,

        @NotEmpty(message = "설명은 필수입니다.")
        @Size(max = 1000, message = "설명은 최대 1000자까지 입력 가능합니다.")
        String description,

        DifficultyLevel difficultyLevel
) {
}
