package com.example.demo.domain.auth.service;

import com.example.demo.domain.auth.dto.AuthRequest;
import com.example.demo.domain.auth.dto.AuthResponse;
import com.example.demo.domain.auth.exception.AuthException;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.global.api.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public AuthResponse.Signup signup(AuthRequest.Signup req) {
		if (userRepository.existsByEmail(req.getEmail())) {
			throw new AuthException(ErrorCode.DUPLICATE_EMAIL);
		}

		User user = User.builder()
			.email(req.getEmail())
			.password(passwordEncoder.encode(req.getPassword()))
			.name(req.getName())
			.build();

		User saved = userRepository.save(user);

		return AuthResponse.Signup.builder()
			.userId(saved.getId())
			.email(saved.getEmail())
			.name(saved.getName())
			.build();
	}
}
