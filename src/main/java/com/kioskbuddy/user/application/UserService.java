package com.kioskbuddy.user.application;

import com.kioskbuddy.common.util.PasswordValidator;
import com.kioskbuddy.user.application.dto.UserRegisterRequest;
import com.kioskbuddy.user.domain.User;
import com.kioskbuddy.user.domain.UserInfo;
import com.kioskbuddy.user.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User register(UserRegisterRequest request) {
        if (userRepository.existsByUserInfoPhoneNumber(request.phoneNumber())) {
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }

        PasswordValidator.validate(request.password());

        UserInfo userInfo = new UserInfo(request.age(), request.getUserTypeEnum(request.userType()), request.phoneNumber());

        User user = User.builder()
                .userInfo(userInfo)
                .password(request.password())
                .build();

        userRepository.save(user);
        return user;
    }
}
