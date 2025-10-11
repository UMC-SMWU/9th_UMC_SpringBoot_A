package com.example.demo.domain.store.entity;


import com.example.demo.global.entity.BaseTimeEntity;
import com.example.demo.domain.location.entity.Location;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "stores")
public class Store extends BaseTimeEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;


	@Column(length = 120, nullable = false)
	private String name;


	@Column(length = 255)
	private String address;


	@Builder.Default
	@OneToMany(mappedBy = "store")
	private List<Mission> missions = new ArrayList<>();


	@Builder.Default
	@OneToMany(mappedBy = "store")
	private List<Review> reviews = new ArrayList<>();
}