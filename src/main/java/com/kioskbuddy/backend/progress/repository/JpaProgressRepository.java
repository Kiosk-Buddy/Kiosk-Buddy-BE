package com.kioskbuddy.backend.progress.repository;

import com.kioskbuddy.backend.progress.domain.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProgressRepository extends JpaRepository<Progress, Long> {
}
