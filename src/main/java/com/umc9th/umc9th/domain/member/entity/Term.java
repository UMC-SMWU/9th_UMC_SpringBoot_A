package com.umc9th.umc9th.domain.member.entity;

import com.umc9th.umc9th.domain.member.enums.FoodName;
import com.umc9th.umc9th.domain.member.enums.TermName;
import com.umc9th.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "term")
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private TermName name;
}
