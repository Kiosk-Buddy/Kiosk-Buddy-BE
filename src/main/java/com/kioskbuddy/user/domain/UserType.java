package com.kioskbuddy.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {

    CHILD("아동"),
    ADULT("성인"),
    SENIOR("고령자"),
    DISABLED("장애인")
    ;

    private final String description;
}
