package com.kioskbuddy.backend.tutorial.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@RequiredArgsConstructor
public enum DifficultyLevel {
    EASY,
    MEDIUM,
    HARD;

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static DifficultyLevel from(String value) {
        return Arrays.stream(DifficultyLevel.values())
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("허용되지 않는 난이도입니다: " + value));
    }
}
