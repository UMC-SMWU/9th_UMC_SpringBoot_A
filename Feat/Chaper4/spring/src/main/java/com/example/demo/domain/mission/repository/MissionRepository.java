
package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.dto.HomeMissionItemDto;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

	@Query(value = """
		select new com.example.demo.dto.HomeMissionItemDto(
		    m.id, m.title, m.condition, m.deadline, m.basePoint,
		    s.id, s.name
		)
		from Mission m
		join m.store s //미션이 속한 가게 정보(가게명,id)를 함께 뽑기 위해 조인
		left join UserMission um
		    on um.mission = m
		   and um.user.id = :userId
		   and um.status  = com.example.demo.domain.mission.constant.UserMissionStatus.COMPLETED
		where s.location.id = :locationId
		  and (m.deadline is null or m.deadline >= :today) // 마감이 없거나 오늘 이후인 것들만
		  and um.id is null //위 조인이 매칭 되지 않은(해당 유저가 완료하지 않은) 미션만 남김 논리적으로 not exists와 동일
		order by m.deadline asc nulls last, m.id asc // 마감 임박한 순(null은 마지막), 같은 날이면 id 오름차순
		""",
		countQuery = """ 
			select count(m.id) //페이징 전체 건수 계산용 조건은 같아 tota값은 위와 같음
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
