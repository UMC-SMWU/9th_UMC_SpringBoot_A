package com.example.demo.domain.review.entity;


import com.example.demo.global.entity.CreatedAtEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review_photos")
public class ReviewPhoto extends CreatedAtEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_photo_id")
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "review_id", nullable = false)
	private Review review;


	@Column(name = "photo_url", length = 255, nullable = false)
	private String photoUrl;
}