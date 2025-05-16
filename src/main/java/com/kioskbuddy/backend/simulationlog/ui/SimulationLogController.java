package com.kioskbuddy.backend.simulationlog.ui;

import com.kioskbuddy.backend.simulationlog.application.SimulationLogService;
import com.kioskbuddy.backend.simulationlog.application.dto.SimulationLogCreateRequest;
import com.kioskbuddy.backend.simulationlog.application.dto.SimulationLogDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulationlog")
@RequiredArgsConstructor
public class SimulationLogController {

    private final SimulationLogService simulationLogService;

    @PostMapping
    public ResponseEntity<Long> createSimulationLog(@RequestBody SimulationLogCreateRequest request) {
        Long simulationLogId = simulationLogService.createSimulationLog(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(simulationLogId);
    }

    @GetMapping("{simulationLogId}")
    public ResponseEntity<SimulationLogDetailResponse> getSimulationLogDetailResponse(@PathVariable Long simulationLogId) {
        SimulationLogDetailResponse response = SimulationLogDetailResponse.from(simulationLogService.getSimulationLog(simulationLogId));
        return ResponseEntity.ok(response);
    }
}
