package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.dto.HomeMissionItemDto;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

	@EntityGraph(attributePaths = {"store"})
	Page<Mission> findByStoreId(Long storeId, Pageable pageable);


	@Query(value = """
		select new com.example.demo.dto.HomeMissionItemDto(
		    m.id, m.title, m.condition, m.deadline, m.basePoint,
		    s.id, s.name
		)
		from Mission m
		join m.store s 
		left join UserMission um
		    on um.mission = m
		   and um.user.id = :userId
		   and um.status  = com.example.demo.domain.mission.constant.UserMissionStatus.COMPLETED
		where s.location.id = :locationId
		  and (m.deadline is null or m.deadline >= :today) 
		  and um.id is null 
		order by m.deadline asc nulls last, m.id asc 
		""",
		countQuery = """ 
			select count(m.id) 
			from Mission m
			join m.store s
			left join UserMission um
			    on um.mission = m
			   and um.user.id = :userId
			   and um.status  = com.example.demo.domain.mission.constant.UserMissionStatus.COMPLETED
			where s.location.id = :locationId
			  and (m.deadline is null or m.deadline >= :today)
			  and um.id is null
			""")
	Page<HomeMissionItemDto> findAvailableForHome( // 현재 지역에서 아직 완료하지 않은 미션 목록 페이징으로 조회
		@Param("userId") Long userId,
		@Param("locationId") Long locationId,
		@Param("today") LocalDate today,
		Pageable pageable);
}
