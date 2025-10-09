package com.example.demo.global.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreatedAtEntity {
	@CreatedDate
	@Column(name = "created_at", updatable = false, nullable = false)
	protected LocalDateTime createdAt;
}