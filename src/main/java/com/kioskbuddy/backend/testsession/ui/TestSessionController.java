package com.kioskbuddy.backend.testsession.ui;

import com.kioskbuddy.backend.testsession.application.TestSessionService;
import com.kioskbuddy.backend.testsession.application.dto.TestSessionCreateRequest;
import com.kioskbuddy.backend.testsession.application.dto.TestSessionDetailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testsession")
@RequiredArgsConstructor
public class TestSessionController {

    private final TestSessionService testSessionService;

    @PostMapping
    public ResponseEntity<Long> createTestSession(@Valid @RequestBody TestSessionCreateRequest request) {
        Long testSessionId = testSessionService.createTestSession(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(testSessionId);
    }

    @GetMapping("/{testSessionId}")
    public ResponseEntity<TestSessionDetailResponse> getTestSession(@PathVariable Long testSessionId) {
        TestSessionDetailResponse response = TestSessionDetailResponse.from(testSessionService.getTestSession(testSessionId));
        return ResponseEntity.ok(response);
    }
}
