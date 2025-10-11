package com.example.demo.domain.mission.entity;


import com.example.demo.global.entity.CreatedAtEntity;
import com.example.demo.domain.mission.constant.UserMissionStatus;
import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "user_missions", indexes = {
	@Index(name = "ux_user_mission_unique", columnList = "user_id, mission_id", unique = true)
})
public class UserMission extends CreatedAtEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_mission_id")
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "mission_id", nullable = false)
	private Mission mission;


	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private UserMissionStatus status; // ASSIGNED, IN_PROGRESS, COMPLETED, CANCELED 등


	@Column(name = "completed_at")
	private LocalDateTime completedAt; // nullable


	@Builder.Default
	@Column(name = "earned_point", nullable = false)
	private Integer earnedPoint = 0;
}