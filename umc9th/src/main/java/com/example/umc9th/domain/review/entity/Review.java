package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    // FK: user_missions_id (NOT NULL)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_missions_id", nullable = false)
    private UserMission userMission;

    // FK: user_id (NOT NULL)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    // FK: store_id (NOT NULL)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // DECIMAL(2,1) ex)3.4
    @Column(name = "rating", nullable = false, precision = 2, scale = 1)
    private BigDecimal rating;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    //(1:N)
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReviewImage> images = new ArrayList<>();

    // 양방향 편의 메서드
    public void addImage(ReviewImage image) {
        images.add(image);
        image.setReview(this);
    }
}