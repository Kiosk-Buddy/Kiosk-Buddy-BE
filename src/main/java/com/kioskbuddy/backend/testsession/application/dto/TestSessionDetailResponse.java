package com.kioskbuddy.backend.testsession.application.dto;

import com.kioskbuddy.backend.testsession.domain.TestSession;

public record TestSessionDetailResponse(

        String memberName,
        String tutorialTitle,
        Integer score
) {
    public static TestSessionDetailResponse from(TestSession testSession) {
        return new TestSessionDetailResponse(
                testSession.getMember().getName(),
                testSession.getTutorial().getTitle(),
                testSession.getScore()
        );
    }
}
