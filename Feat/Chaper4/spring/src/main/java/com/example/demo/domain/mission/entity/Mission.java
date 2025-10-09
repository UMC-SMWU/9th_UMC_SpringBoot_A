package com.example.demo.domain.mission.entity;


import com.example.demo.global.entity.CreatedAtEntity;
import com.example.demo.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "missions")
public class Mission extends CreatedAtEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mission_id")
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;


	@Column(length = 120, nullable = false)
	private String title;


	@Column(length = 255)
	private String condition;


	private LocalDate deadline; // nullable 허용


	@Column(name = "base_point", nullable = false)
	private Integer basePoint = 0;


	@OneToMany(mappedBy = "mission")
	private List<UserMission> userMissions = new ArrayList<>();
}