package com.kioskbuddy.backend.tutorial.domain;

import com.kioskbuddy.backend.common.domain.BaseTimeEntity;
import com.kioskbuddy.backend.tutorial.application.dto.TutorialUpdateRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tutorial")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Tutorial extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "tutorial_id", updatable = false, nullable = false)
    private Long id;


    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficultyLevel;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Category category;

    @Builder
    public Tutorial(String title, String description, DifficultyLevel difficultyLevel) {
        this.title = title;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
    }

    public void update(TutorialUpdateRequest request) {
        this.title = request.title();
        this.description = request.description();
        this.difficultyLevel = DifficultyLevel.valueOf(request.difficultyLevel().name());
    }
}
