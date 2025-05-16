package com.kioskbuddy.backend.simulationlog.repository.jpa;

import com.kioskbuddy.backend.simulationlog.domain.SimulationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaSimulationLogRepository extends JpaRepository<SimulationLog, Long> {

    @Query("SELECT s FROM SimulationLog s " +
            "JOIN FETCH s.member " +
            "JOIN FETCH s.tutorial " +
            "WHERE s.id = :id")
    Optional<SimulationLog> findWithMemberAndTutorialById(@Param("id") Long id);
}
