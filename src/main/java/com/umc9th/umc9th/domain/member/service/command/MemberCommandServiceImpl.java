package com.umc9th.umc9th.domain.member.service.command;

import com.umc9th.umc9th.domain.member.converter.MemberConverter;
import com.umc9th.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.umc9th.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.umc9th.umc9th.domain.member.entity.Food;
import com.umc9th.umc9th.domain.member.entity.Member;
import com.umc9th.umc9th.domain.member.entity.mapping.MemberFood;
import com.umc9th.umc9th.domain.member.exception.MemberException;
import com.umc9th.umc9th.domain.member.exception.code.MemberErrorCode;
import com.umc9th.umc9th.domain.member.repository.FoodRepository;
import com.umc9th.umc9th.domain.member.repository.MemberFoodRepository;
import com.umc9th.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    @Override
    public MemberResponseDTO.JoinDTO signUp(MemberRequestDTO.JoinDTO dto) {
        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

        // 선호 음식 존재 여부
        if (dto.preferCategory().size() > 1){
            List<MemberFood> memberFoodList = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new MemberException(MemberErrorCode.NO_FOOD))
                            ).build()
                    ).toList();

            /*
            // for문으로 구현
            for (Long id: dto.preferCategory()){
                // 음식 존재 여부 검증
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new MemberException(MemberErrorCode.NO_FOOD));

                // MemberFood 엔티티 생성 (컨버터 사용)
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();
                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(memberFood);
            }
            */


            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList);
        }

        return MemberConverter.toJoinDTO(member);
    }
}
