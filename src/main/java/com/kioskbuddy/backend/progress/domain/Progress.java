package com.kioskbuddy.backend.progress.domain;

import com.kioskbuddy.backend.common.domain.BaseTimeEntity;
import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Progress extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "progress_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorial_id", nullable = false)
    private Tutorial tutorial;

    @Column(nullable = false)
    private Float progressPercentage;

    private LocalDateTime completedAt;

    @Builder
    private Progress(Member member, Tutorial tutorial, Float progressPercentage) {
        this.member = member;
        this.tutorial = tutorial;
        this.progressPercentage = progressPercentage;
    }

    public static Progress create(Member member, Tutorial tutorial, Float progressPercentage) {
        return Progress.builder()
                .member(member)
                .tutorial(tutorial)
                .progressPercentage(progressPercentage)
                .build();
    }
}
