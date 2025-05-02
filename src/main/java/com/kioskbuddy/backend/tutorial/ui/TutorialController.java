package com.kioskbuddy.backend.tutorial.ui;

import com.kioskbuddy.backend.tutorial.application.TutorialService;
import com.kioskbuddy.backend.tutorial.application.dto.TutorialCreateRequest;
import com.kioskbuddy.backend.tutorial.application.dto.TutorialDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutorial")
public class TutorialController {

    private final TutorialService tutorialService;

    @PostMapping
    public ResponseEntity<Long> createTutorial(@RequestBody TutorialCreateRequest request) {
        Long tutorialId = tutorialService.createTutorial(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorialId);
    }

    @GetMapping("/{tutorialId}")
    public ResponseEntity<TutorialDetailResponse> getTutorial(@PathVariable Long tutorialId) {
        TutorialDetailResponse response = new TutorialDetailResponse(tutorialService.getTutorial(tutorialId));
        return ResponseEntity.ok(response);
    }
}
