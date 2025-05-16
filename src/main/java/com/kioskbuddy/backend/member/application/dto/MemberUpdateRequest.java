package com.kioskbuddy.backend.member.application.dto;

import com.kioskbuddy.backend.member.domain.MemberType;
import jakarta.validation.constraints.*;

public record MemberUpdateRequest(
        @NotEmpty(message = "이름은 필수입니다.")
        @Pattern(
                regexp = "^[가-힣]{2,5}$",
                message = "이름은 2자 이상 5자 이하의 한글이어야 합니다."
        )
        String name,

        @NotNull(message = "나이는 필수입니다.")
        @Min(value = 5, message = "나이는 최소 5 이상이어야 합니다.")
        @Max(value = 100, message = "나이는 최대 100 이하여야 합니다.")
        Integer age,

        @NotEmpty(message = "전화번호는 필수입니다.")
        @Pattern(
                regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",
                message = "전화번호는 '000-0000-0000' 형식이어야 합니다."
        ) String phoneNumber,

        MemberType memberType
) {
}
