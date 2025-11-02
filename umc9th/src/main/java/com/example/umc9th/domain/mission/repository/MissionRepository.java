package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = """
            select m
            from Mission m
            join fetch m.store s
            join fetch s.region r
            where r.id = :regionId
            order by m.createdAt desc
            """,
            countQuery = """
            select count(m)
            from Mission m
            join m.store s
            join s.region r
            where r.id = :regionId
            """)
    Page<Mission> findPageByRegionWithStore(
            @Param("regionId") Long regionId,
            Pageable pageable
    );
}