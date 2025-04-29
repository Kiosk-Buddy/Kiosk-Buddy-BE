package com.kioskbuddy.backend.member.application;

import com.kioskbuddy.backend.member.application.dto.MemberDetailResponse;
import com.kioskbuddy.backend.member.application.dto.MemberSignupRequest;
import com.kioskbuddy.backend.member.application.dto.MemberUpdateRequest;
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
    public Long signupMember(MemberSignupRequest request) {
        if (memberRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }

        Member member = Member
                .builder()
                .name(request.name())
                .age(request.age())
                .phoneNumber(request.phoneNumber())
                .password(request.password())
                .build();

        return memberRepository.save(member).getId();
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        member.update(request);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        memberRepository.delete(member);
    }
}
