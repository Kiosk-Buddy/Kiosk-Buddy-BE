package com.kioskbuddy.backend.progress.repository;

import com.kioskbuddy.backend.progress.domain.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaProgressRepository extends JpaRepository<Progress, Long> {

    @Query("SELECT p FROM Progress p " +
            "JOIN FETCH p.member " +
            "JOIN FETCH p.tutorial " +
            "WHERE p.id = :id")
    Optional<Progress> findWithMemberAndTutorialById(@Param("id") Long id);
}
