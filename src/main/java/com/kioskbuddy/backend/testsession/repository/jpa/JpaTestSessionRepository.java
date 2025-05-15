package com.kioskbuddy.backend.testsession.repository.jpa;

import com.kioskbuddy.backend.testsession.domain.TestSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTestSessionRepository extends JpaRepository<TestSession, Long> {
}
