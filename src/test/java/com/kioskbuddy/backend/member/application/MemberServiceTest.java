package com.kioskbuddy.backend.member.application;

import com.kioskbuddy.backend.member.application.dto.MemberSignupRequest;
import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private JpaMemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 테스트")
    void signupMember_success() {
        // given
        MemberSignupRequest request = new MemberSignupRequest("홍길동", 30L, "010-1234-5678", "Abc123!@#");
        Member member = Member.builder()
                .name(request.name())
                .age(request.age())
                .phoneNumber(request.phoneNumber())
                .password(request.password())
                .build();

        ReflectionTestUtils.setField(member, "id", 1L);

        given(memberRepository.existsByPhoneNumber(request.phoneNumber())).willReturn(false);
        given(memberRepository.save(any(Member.class))).willReturn(member);
        
        // when
        Long memberId = memberService.signupMember(request);
        
        // then
        assertEquals(1L, memberId);
        verify(memberRepository).existsByPhoneNumber(request.phoneNumber());
        verify(memberRepository).save(any(Member.class));
    }
    
    @Test
    @DisplayName("중복 전화번호 예외 테스트")
    void signupMember_duplicatePhoneNumber_throwsException() {
        // given
        MemberSignupRequest request = new MemberSignupRequest("홍길동", 30L, "010-1234-5678", "Abc123!@#");
        given(memberRepository.existsByPhoneNumber(request.phoneNumber())).willReturn(true);
        
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.signupMember(request);
        });
    } 
}