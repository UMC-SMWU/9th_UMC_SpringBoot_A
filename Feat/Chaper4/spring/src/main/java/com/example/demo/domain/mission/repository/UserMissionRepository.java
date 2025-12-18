package com.example.demo.domain.mission.repository;

import java.util.Optional;

import com.example.demo.domain.mission.entity.UserMission;
import com.example.demo.domain.mission.constant.UserMissionStatus;
import com.example.demo.dto.UserMissionItemDto;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

	@EntityGraph(attributePaths = {"user", "mission", "mission.store"})
	Optional<UserMission> findWithAllById(Long userMissionId);

	@Query(value = """
        select new com.example.demo.dto.UserMissionItemDto(
            um.id, um.status, um.createdAt, um.completedAt,
            m.id, m.title, m.deadline,
            s.id, s.name
        )
        from UserMission um
        join um.mission m
        join m.store s
        where um.user.id = :userId
          and um.status   = :status
        order by um.createdAt desc 
        """,
		countQuery = """
        select count(um.id)
        from UserMission um
        where um.user.id = :userId
          and um.status   = :status
        """)
	Page<UserMissionItemDto> findListByUserAndStatus(
		@Param("userId") Long userId,
		@Param("status") UserMissionStatus status,
		Pageable pageable);


	default Page<UserMissionItemDto> findPending(Long userId, Pageable pageable) {
		return findListByUserAndStatus(userId, UserMissionStatus.IN_PROGRESS, pageable);
	}
	default Page<UserMissionItemDto> findCompleted(Long userId, Pageable pageable) {
		return findListByUserAndStatus(userId, UserMissionStatus.COMPLETED, pageable);
	}

	// 홈에 도넛 용: 해당 지역에서 완료한 미션 수 UI에서 게이지는 count / 10.0 처럼 표시
	@Query("""
        select count(um.id)
        from UserMission um
        join um.mission m
        join m.store s
        where um.user.id = :userId
          and um.status  = com.example.demo.domain.mission.constant.UserMissionStatus.COMPLETED
          and s.location.id = :locationId
        """)
	long countCompletedInLocation(@Param("userId") Long userId,
		@Param("locationId") Long locationId);

	boolean existsByUserIdAndMissionId(Long userId, Long missionId);
}

