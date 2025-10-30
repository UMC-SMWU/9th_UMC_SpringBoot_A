package com.example.demo.domain.user.repository;

import com.example.demo.domain.user.entity.User;
import com.example.demo.dto.MyPageDto;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("""
        select new com.example.demo.dto.MyPageDto(u.name, u.email, u.point)
        from User u
        where u.id = :userId
        """)
	Optional<MyPageDto> findMyPage(@Param("userId") Long userId);
}