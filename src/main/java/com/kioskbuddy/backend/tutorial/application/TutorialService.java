package com.kioskbuddy.backend.tutorial.application;

import com.kioskbuddy.backend.tutorial.application.dto.TutorialCreateRequest;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import com.kioskbuddy.backend.tutorial.repository.JpaTutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TutorialService {

    private final JpaTutorialRepository tutorialRepository;

    @Transactional
    public Long createTutorial(TutorialCreateRequest request) {
        Tutorial tutorial = Tutorial.builder()
                .title(request.title())
                .description(request.description())
                .difficultyLevel(request.difficultyLevel())
                .build();

        return tutorialRepository.save(tutorial).getId();
    }
}
