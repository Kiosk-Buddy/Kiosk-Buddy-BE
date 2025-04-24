package com.kioskbuddy.backend.member.domain;

import com.kioskbuddy.backend.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false, nullable = false)
    private Long id;

    @NotEmpty(message = "이름은 필수입니다.")
    @Pattern(
            regexp = "^[가-힣]{2,5}$",
            message = "이름은 2자 이상 5자 이하의 한글이어야 합니다."
    )
    private String name;

    @NotNull(message = "나이는 필수입니다.")
    @Min(value = 5, message = "나이는 최소 5 이상이어야 합니다.")
    @Max(value = 100, message = "나이는 최대 100 이하여야 합니다.")
    private Long age;

    @NotEmpty(message = "전화번호는 필수입니다.")
    @Pattern(
            regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",
            message = "전화번호는 '000-0000-0000' 형식이어야 합니다."
    )
    private String phoneNumber;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "비밀번호는 최소 8자 이상, 하나 이상의 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;
}
