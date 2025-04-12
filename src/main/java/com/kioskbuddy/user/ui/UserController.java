package com.kioskbuddy.user.ui;

import com.kioskbuddy.user.application.UserService;
import com.kioskbuddy.user.application.dto.UserRegisterRequest;
import com.kioskbuddy.user.application.dto.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
        UserRegisterResponse response = UserRegisterResponse.userRegisterResponse(userService.register(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
