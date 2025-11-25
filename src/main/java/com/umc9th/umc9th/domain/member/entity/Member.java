package com.umc9th.umc9th.domain.member.entity;

import com.umc9th.umc9th.domain.member.entity.mapping.MemberFood;
import com.umc9th.umc9th.domain.member.entity.mapping.MemberTerm;
import com.umc9th.umc9th.domain.member.enums.Address;
import com.umc9th.umc9th.domain.member.enums.Gender;
import com.umc9th.umc9th.domain.member.enums.SocialType;
import com.umc9th.umc9th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 3, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Address address = Address.NONE;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(name = "social_uid", nullable = false)
    private String socialUid;

    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberFood> memberFoods = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberTerm> memberTerms = new ArrayList<>();

}
