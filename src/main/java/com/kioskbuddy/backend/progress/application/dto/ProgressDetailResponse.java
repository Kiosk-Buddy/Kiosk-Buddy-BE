package com.kioskbuddy.backend.progress.application.dto;

import com.kioskbuddy.backend.progress.domain.Progress;

public record ProgressDetailResponse(

        String memberName,
        String tutorialTitle,
        Float progressPercentage
) {
    public static ProgressDetailResponse from(Progress progress) {
        return new ProgressDetailResponse(
                progress.getMember().getName(),
                progress.getTutorial().getTitle(),
                progress.getProgressPercentage());
    }
}
