package com.kioskbuddy.backend.progress.ui;

import com.kioskbuddy.backend.progress.application.ProgressService;
import com.kioskbuddy.backend.progress.application.dto.ProgressCreateRequest;
import com.kioskbuddy.backend.progress.application.dto.ProgressDetailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping
    public ResponseEntity<Long> createProgress(@Valid @RequestBody ProgressCreateRequest request) {
        Long progressId = progressService.createProgress(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(progressId);
    }

    @GetMapping("/{progressId}")
    public ResponseEntity<ProgressDetailResponse> getProgress(@PathVariable Long progressId) {
        ProgressDetailResponse response = ProgressDetailResponse.from(progressService.getProgress(progressId));
        return ResponseEntity.ok(response);
    }
}
