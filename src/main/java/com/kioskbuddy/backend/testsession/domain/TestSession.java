package com.kioskbuddy.backend.testsession.domain;

import com.kioskbuddy.backend.common.domain.BaseTimeEntity;
import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_session")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class TestSession extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "test_session_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorial_id", nullable = false)
    private Tutorial tutorial;

    private Integer score;

    private LocalDateTime completedAt;

    @Builder
    private TestSession(Member member, Tutorial tutorial, Integer score) {
        this.member = member;
        this.tutorial = tutorial;
        this.score = score;
    }

    public static TestSession create(Member member, Tutorial tutorial, Integer score) {
        return TestSession.builder()
                .member(member)
                .tutorial(tutorial)
                .score(score)
                .build();
    }
}
