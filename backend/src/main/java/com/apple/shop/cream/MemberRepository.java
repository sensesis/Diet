package com.apple.shop.cream;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // JpaRepository를 상속하여 기본 CRUD 작업이 자동으로 제공됩니다.
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPassword(String password);
}
