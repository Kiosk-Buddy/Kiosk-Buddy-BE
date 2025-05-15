package com.kioskbuddy.backend.testsession.repository.jpa;

import com.kioskbuddy.backend.testsession.domain.TestSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaTestSessionRepository extends JpaRepository<TestSession, Long> {

    @Query("SELECT t FROM TestSession t " +
            "JOIN FETCH t.member " +
            "JOIN FETCH t.tutorial " +
            "WHERE t.id = :id")
    Optional<TestSession> findWithMemberAndTutorialById(@Param("id") Long id);
}
