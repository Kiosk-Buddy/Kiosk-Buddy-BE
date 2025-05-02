package com.kioskbuddy.backend.member.repository.jpa;

import com.kioskbuddy.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    boolean existsByPhoneNumber(String phoneNumber);
}
