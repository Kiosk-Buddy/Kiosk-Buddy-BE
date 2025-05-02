package com.kioskbuddy.backend.tutorial.ui;

import com.kioskbuddy.backend.tutorial.application.TutorialService;
import com.kioskbuddy.backend.tutorial.application.dto.TutorialCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TutorialController {

     private final TutorialService tutorialService;

     @PostMapping("/tutorials")
     public ResponseEntity<Long> createTutorial(@RequestBody TutorialCreateRequest request) {
         Long tutorialId = tutorialService.createTutorial(request);
         return ResponseEntity.status(HttpStatus.CREATED).body(tutorialId);
     }
}
