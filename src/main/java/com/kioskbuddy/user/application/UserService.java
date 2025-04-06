package com.kioskbuddy.user.application;

import com.kioskbuddy.user.application.dto.UserRegisterRequest;
import com.kioskbuddy.user.application.dto.UserRegisterResponse;
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
    public UserRegisterResponse register(UserRegisterRequest dto) {
        if (userRepository.existsByUserInfoPhoneNumber(dto.phoneNumber())) {
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }

        UserInfo userInfo = new UserInfo(dto.age(), dto.getUserTypeEnum(dto.userType()), dto.phoneNumber());

        User user = User.builder()
                .userInfo(userInfo)
                .password(dto.password())
                .build();

        userRepository.save(user);
        return new UserRegisterResponse(user);
    }
}
