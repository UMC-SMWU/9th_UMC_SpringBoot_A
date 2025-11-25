package com.umc9th.umc9th.domain.member.repository;

import com.umc9th.umc9th.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
