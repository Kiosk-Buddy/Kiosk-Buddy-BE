package com.kioskbuddy.backend.simulationlog.domain;

import com.kioskbuddy.backend.common.domain.BaseTimeEntity;
import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.tutorial.domain.Tutorial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "simulation_log")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class SimulationLog extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "simulation_log_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorial_id", nullable = false)
    private Tutorial tutorial;

    private Boolean isSuccess;

    @Builder
    private SimulationLog(Member member, Tutorial tutorial, Boolean isSuccess) {
        this.member = member;
        this.tutorial = tutorial;
        this.isSuccess = isSuccess;
    }

    public static SimulationLog create(Member member, Tutorial tutorial, Boolean isSuccess) {
        return SimulationLog.builder()
                .member(member)
                .tutorial(tutorial)
                .isSuccess(isSuccess)
                .build();
    }
}
