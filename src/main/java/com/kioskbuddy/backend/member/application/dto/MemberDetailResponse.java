package com.kioskbuddy.backend.member.application.dto;

import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.domain.MemberType;

public record MemberDetailResponse(
        Long id,
        String name,
        Integer age,
        String phoneNumber,
        MemberType memberType
) {
    public static MemberDetailResponse from(Member member) {
        return new MemberDetailResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getPhoneNumber(),
                member.getMemberType()
        );
    }
}
