package com.kioskbuddy.backend.tutorial.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum DifficultyLevel {
    EASY,
    MEDIUM,
    HARD;

    @JsonCreator
    public static DifficultyLevel from(String value) {
        return DifficultyLevel.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase();
    }
}
