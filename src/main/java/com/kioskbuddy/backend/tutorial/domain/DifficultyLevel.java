package com.kioskbuddy.backend.tutorial.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DifficultyLevel {
    EASY,
    MEDIUM,
    HARD;

//    public static DifficultyLevel fromString(String level) {
//        switch (level.toUpperCase()) {
//            case "EASY":
//                return EASY;
//            case "MEDIUM":
//                return MEDIUM;
//            case "HARD":
//                return HARD;
//            default:
//                throw new IllegalArgumentException("Unknown difficulty level: " + level);
//        }
//    }
}
