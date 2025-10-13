package com.example.umc9th.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "review_image")
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_image_id")
    private Long id;

    // FK: review_id (NOT NULL)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(name = "review_image_url", nullable = false, length = 200)
    private String reviewImageUrl;

    // 편의 setter
    public void setReview(Review review) {
        this.review = review;
    }
}