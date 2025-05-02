package com.kioskbuddy.backend.tutorial.application.dto;

import com.kioskbuddy.backend.tutorial.domain.DifficultyLevel;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;

public record TutorialDetailResponse(

        String title,
        String description,
        DifficultyLevel difficultyLevel
) {

    public TutorialDetailResponse(Tutorial tutorial) {
        this(
                tutorial.getTitle(),
                tutorial.getDescription(),
                tutorial.getDifficultyLevel()
        );
    }
}
