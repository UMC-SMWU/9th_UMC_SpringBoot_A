package com.example.demo.domain.region.entity;


import com.example.demo.domain.location.entity.Location;
import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "region_bonus", indexes = {
	@Index(name = "ux_region_bonus_user_location", columnList = "user_id, location_id", unique = true)
})
public class RegionBonus {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_bonus_id")
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;


	@Column(nullable = false)
	private Integer threshold;


	@Column(name = "bonus_point", nullable = false)
	private Integer bonusPoint;


	@Column(name = "awarded_at")
	private LocalDateTime awardedAt; // nullable
}