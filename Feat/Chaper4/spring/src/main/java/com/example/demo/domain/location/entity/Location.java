package com.example.demo.domain.location.entity;


import com.example.demo.domain.region.entity.RegionBonus;
import com.example.demo.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "locations")
public class Location {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Long id;


	@Column(length = 100, nullable = false, unique = true)
	private String name;


	@OneToMany(mappedBy = "location")
	private List<Store> stores = new ArrayList<>();


	@OneToMany(mappedBy = "location")
	private List<RegionBonus> regionBonuses = new ArrayList<>();
}