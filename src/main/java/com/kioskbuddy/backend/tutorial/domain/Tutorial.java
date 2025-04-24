package com.kioskbuddy.backend.tutorial.domain;

import com.kioskbuddy.backend.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tutorial")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Tutorial extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "tutorial_id", updatable = false, nullable = false)
    private Long id;

    @NotEmpty(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
    @Column(length = 100, nullable = false)
    private String title;

    @NotEmpty(message = "설명은 필수입니다.")
    @Size(max = 1000, message = "설명은 최대 1000자까지 입력 가능합니다.")
    @Column(length = 1000, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficultyLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
}
