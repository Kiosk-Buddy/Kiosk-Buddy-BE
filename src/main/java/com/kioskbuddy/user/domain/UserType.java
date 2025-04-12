package com.kioskbuddy.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {

    CHILD("아동"),
    ADULT("성인"),
    SENIOR("고령자"),
    DISABLED("장애인")
    ;

    private final String description;
}
