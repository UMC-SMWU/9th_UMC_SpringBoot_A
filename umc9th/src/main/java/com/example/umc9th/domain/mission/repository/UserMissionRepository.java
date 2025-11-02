package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Query(value = """
            select um
            from UserMission um
            join fetch um.mission m
            join fetch m.store s
            where um.member.id = :memberId
              and um.status = :status
            order by um.assignedAt desc
            """,
            countQuery = """
            select count(um)
            from UserMission um
            where um.member.id = :memberId
              and um.status = :status
            """)
    Page<UserMission> findPageWithMissionAndStoreByStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}
