package com.umc9th.umc9th.domain.member.repository;

import com.umc9th.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
