package org.myungkeun.shop_study.repository;

import org.myungkeun.shop_study.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Number> {
}
