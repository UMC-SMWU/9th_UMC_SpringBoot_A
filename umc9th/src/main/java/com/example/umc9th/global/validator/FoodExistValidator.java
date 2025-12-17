package com.example.umc9th.global.validator;

import com.example.umc9th.domain.food.repository.FoodRepository;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Long>> {

    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(List<Long> ids, ConstraintValidatorContext context) {

        // 1) null/빈 배열이면 "통과" 시키고 싶다 -> true
        if (ids == null || ids.isEmpty()) return true;

        // 2) 하나라도 없으면 실패
        for (Long id : ids) {
            if (id == null || !foodRepository.existsById(id)) {
                return false;
            }
        }
        return true;
    }
}