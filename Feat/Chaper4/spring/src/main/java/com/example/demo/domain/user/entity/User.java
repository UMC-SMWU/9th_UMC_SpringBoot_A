package com.example.demo.domain.user.entity;


import com.example.demo.global.entity.BaseTimeEntity;
import com.example.demo.domain.mission.entity.UserMission;
import com.example.demo.domain.region.entity.RegionBonus;
import com.example.demo.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = {
	@UniqueConstraint(name = "uk_users_email", columnNames = "email")
})
public class User extends BaseTimeEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;


	@Column(length = 100, nullable = false)
	private String email;


	@Column(length = 255, nullable = false)
	private String password;


	@Column(length = 50, nullable = false)
	private String name;


	@Column(nullable = false)
	private Integer point = 0;


	@OneToMany(mappedBy = "user")
	private List<Review> reviews = new ArrayList<>();


	@OneToMany(mappedBy = "user")
	private List<UserMission> userMissions = new ArrayList<>();


	@OneToMany(mappedBy = "user")
	private List<RegionBonus> regionBonuses = new ArrayList<>();
}