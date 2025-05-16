package com.kioskbuddy.backend.member.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberType {
    GENERAL("일반 사용자"),
    SENIOR("고령층 사용자"),
    DISABLED("장애인 사용자");

    private final String description;
}
