package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "User_missions")
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_missions_id")
    private Long id;

    // FK: user_id (NOT NULL)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    // FK: mission_id (NOT NULL)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    // VARCHAR(20)
    @Enumerated(EnumType.STRING)
    @Column(name="status", length=20)
    private MissionStatus status;

    @PrePersist
    void onCreate(){
        LocalDateTime createdAt = null;
        if(createdAt==null) createdAt=LocalDateTime.now();
        if(status==null) status=MissionStatus.BEFORE_START;
    }

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

}