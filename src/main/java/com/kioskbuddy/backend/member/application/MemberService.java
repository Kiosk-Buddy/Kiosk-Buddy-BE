package com.kioskbuddy.backend.member.application;

import com.kioskbuddy.backend.member.application.dto.MemberSignupRequest;
import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final JpaMemberRepository memberRepository;

    @Transactional
    public Long signupMember(MemberSignupRequest dto) {
        if (memberRepository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }

        Member member = Member
                .builder()
                .name(dto.name())
                .age(dto.age())
                .phoneNumber(dto.phoneNumber())
                .password(dto.password())
                .build();

        return memberRepository.save(member).getId();
    }
}
