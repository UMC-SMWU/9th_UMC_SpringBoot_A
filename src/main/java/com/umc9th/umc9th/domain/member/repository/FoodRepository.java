package com.umc9th.umc9th.domain.member.repository;

import com.umc9th.umc9th.domain.member.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
