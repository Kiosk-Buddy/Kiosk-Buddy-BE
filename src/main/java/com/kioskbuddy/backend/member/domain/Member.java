package com.kioskbuddy.backend.member.domain;

import com.kioskbuddy.backend.common.domain.BaseTimeEntity;
import com.kioskbuddy.backend.member.application.dto.MemberUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false, nullable = false)
    private Long id;

    private String name;

    private Integer age;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    private String password;

    @Builder
    private Member(String name, Integer age, String phoneNumber, String password) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public static Member create(String name, Integer age, String phoneNumber, String password) {
        return Member.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .password(password)
                .build();
    }
}
