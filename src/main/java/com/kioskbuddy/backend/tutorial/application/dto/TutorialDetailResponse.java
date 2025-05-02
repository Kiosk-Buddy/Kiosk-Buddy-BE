package com.kioskbuddy.backend.tutorial.application.dto;

import com.kioskbuddy.backend.tutorial.domain.Tutorial;

public record TutorialDetailResponse(

        String title,
        String description,
        String difficultyLevel
) {

    public TutorialDetailResponse(Tutorial tutorial) {
        this(
                tutorial.getTitle(),
                tutorial.getDescription(),
                tutorial.getDifficultyLevel().toValue()
        );
    }
}
