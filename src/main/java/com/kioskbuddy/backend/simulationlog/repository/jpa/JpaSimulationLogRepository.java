package com.kioskbuddy.backend.simulationlog.repository.jpa;

import com.kioskbuddy.backend.simulationlog.domain.SimulationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSimulationLogRepository extends JpaRepository<SimulationLog, Long> {
}
