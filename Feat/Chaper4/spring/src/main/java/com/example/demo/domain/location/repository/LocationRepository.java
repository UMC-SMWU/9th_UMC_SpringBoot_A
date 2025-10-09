package com.example.demo.domain.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.location.entity.Location;

public interface LocationRepository extends JpaRepository <Location,Long> {
}
