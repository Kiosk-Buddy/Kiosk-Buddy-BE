package com.kioskbuddy.backend.member.ui;

import com.kioskbuddy.backend.member.application.MemberService;
import com.kioskbuddy.backend.member.application.dto.MemberDetailResponse;
import com.kioskbuddy.backend.member.application.dto.MemberSignupRequest;
import com.kioskbuddy.backend.member.application.dto.MemberUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@Valid @RequestBody MemberSignupRequest request) {
        Long memberId = memberService.signupMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDetailResponse> getMember(@PathVariable Long memberId) {
        MemberDetailResponse response = MemberDetailResponse.from(memberService.getMember(memberId));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(@PathVariable Long memberId,
                                             @Valid @RequestBody MemberUpdateRequest request) {
        memberService.updateMember(memberId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
