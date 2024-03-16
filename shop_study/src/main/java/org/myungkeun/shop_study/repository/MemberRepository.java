package org.myungkeun.shop_study.repository;

import org.myungkeun.shop_study.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Number> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNameOrEmail(String name, String email);

    Optional<Member> findByName(String name);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
}
