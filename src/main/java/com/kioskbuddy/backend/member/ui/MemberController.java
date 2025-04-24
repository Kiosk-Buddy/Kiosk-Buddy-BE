package com.kioskbuddy.backend.member.ui;

import com.kioskbuddy.backend.member.application.MemberService;
import com.kioskbuddy.backend.member.application.dto.MemberSignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@Valid @RequestBody MemberSignupRequest request) {
        Long memberId = memberService.signupMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }
}
