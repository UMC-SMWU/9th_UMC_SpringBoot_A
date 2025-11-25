package com.umc9th.umc9th.global.validator;

import com.umc9th.umc9th.domain.member.exception.code.MemberErrorCode;
import com.umc9th.umc9th.domain.member.repository.FoodRepository;
import com.umc9th.umc9th.global.annotation.ExistFoods;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Long>> {

    private final FoodRepository foodRepository;

    // Food가 존재하는지 Validate 한다
    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {

        boolean isValid = values.stream()
                .allMatch(value -> foodRepository.existsById(value));

        if (!isValid) {
            // 이 부분에서 아까 디폴트 메시지를 초기화하고,
            // 새로운 메시지로 덮어씌우게 된다

            // validation 어노테이션이 가진 기본 에러 메시지를 비활성화
            context.disableDefaultConstraintViolation();
            // 커스텀 에러 메시지를 만들어서 Validator에 추가
            context.buildConstraintViolationWithTemplate(MemberErrorCode.NO_FOOD.getMessage())
                    .addConstraintViolation();
        }
        return isValid;
    }
}
