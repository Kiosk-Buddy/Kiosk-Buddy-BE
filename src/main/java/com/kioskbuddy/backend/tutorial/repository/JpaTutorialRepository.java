package com.kioskbuddy.backend.tutorial.repository;

import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTutorialRepository extends JpaRepository<Tutorial, Long> {
}
