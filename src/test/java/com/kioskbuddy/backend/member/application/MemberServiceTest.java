package com.kioskbuddy.backend.member.application;

import com.kioskbuddy.backend.member.application.dto.MemberSignupRequest;
import com.kioskbuddy.backend.member.application.dto.MemberUpdateRequest;
import com.kioskbuddy.backend.member.domain.Member;
import com.kioskbuddy.backend.member.repository.jpa.JpaMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        MemberSignupRequest request = new MemberSignupRequest("홍길동", 30, "010-1234-5678", "Abc123!@#");
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
        MemberSignupRequest request = new MemberSignupRequest("홍길동", 30, "010-1234-5678", "Abc123!@#");
        given(memberRepository.existsByPhoneNumber(request.phoneNumber())).willReturn(true);
        
        // when & then
        assertThrows(IllegalArgumentException.class, () ->
            memberService.signupMember(request)
        );
    }

    // TODO: Member로 비교하도록 수정
    @Test
    @DisplayName("Member 정보 조회 테스트")
    void getMemberTest() {
        // given
        Long memberId = 1L;
        Member member = Member.builder()
                .name("홍길동")
                .age(30)
                .phoneNumber("010-1234-5678")
                .password("Abc123!@#")
                .build();

        // when
        given(memberRepository.findById(memberId)).willReturn(java.util.Optional.of(member));

        // then
        Member result = memberService.getMember(memberId);
        assertEquals(member.getName(), result.getName());
        assertEquals(member.getAge(), result.getAge());
        assertEquals(member.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    @DisplayName("Member 정보 업데이트 테스트")
    void updateMemberTest() {
        // given
        Long memberId = 1L;
        Member member = Member.builder()
                .name("홍길동")
                .age(30)
                .phoneNumber("010-1234-5678")
                .password("Abc123!@#")
                .build();

        MemberUpdateRequest request = new MemberUpdateRequest("김철수", 35, "010-9876-5432");
        given(memberRepository.findById(memberId)).willReturn(java.util.Optional.of(member));
        
        // when
        memberService.updateMember(memberId, request);
        
        // then
        assertEquals(request.name(), member.getName());
        assertEquals(request.age(), member.getAge());
        assertEquals(request.phoneNumber(), member.getPhoneNumber());
        verify(memberRepository).findById(memberId);
    }

    @Test
    @DisplayName("Member 삭제 테스트")
    void deleteMemberTest() {
        // given
        Long memberId = 1L;
        Member member = Member.builder()
                .name("홍길동")
                .age(30)
                .phoneNumber("010-1234-5678")
                .password("Abc123!@#")
                .build();

        given(memberRepository.findById(memberId)).willReturn(java.util.Optional.of(member));

        // when
        memberService.deleteMember(memberId);

        // then
        verify(memberRepository).delete(member);
    }
}