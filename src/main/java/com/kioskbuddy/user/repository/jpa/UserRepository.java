package com.kioskbuddy.user.repository.jpa;

import com.kioskbuddy.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserInfoPhoneNumber(String phoneNumber);
}
