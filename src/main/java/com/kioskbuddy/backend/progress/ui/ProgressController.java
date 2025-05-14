package com.kioskbuddy.backend.progress.ui;

import com.kioskbuddy.backend.progress.application.ProgressService;
import com.kioskbuddy.backend.progress.application.dto.CreateProgressRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping
    public ResponseEntity<Long> createProgress(@Valid @RequestBody CreateProgressRequest request) {
        Long progressId = progressService.createProgress(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(progressId);
    }
}
