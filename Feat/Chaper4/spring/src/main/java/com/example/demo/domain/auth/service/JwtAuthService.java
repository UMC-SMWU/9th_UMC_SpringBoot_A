package com.example.demo.domain.auth.service;

import com.example.demo.domain.auth.dto.AuthRequest;
import com.example.demo.domain.auth.dto.AuthResponse;
import com.example.demo.domain.auth.exception.AuthException;
import com.example.demo.domain.auth.security.CustomUserDetails;
import com.example.demo.domain.auth.security.jwt.JwtTokenProvider;
import com.example.demo.global.api.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;

	public AuthResponse.JwtLogin login(AuthRequest.Login req) {
		try {
			Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
			);

			CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
			String access = jwtTokenProvider.createAccessToken(principal.getUserId(), principal.getEmail());

			return AuthResponse.JwtLogin.builder()
				.userId(principal.getUserId())
				.accessToken(access)
				.build();

		} catch (AuthenticationException e) {
			throw new AuthException(ErrorCode.INVALID_CREDENTIALS);
		}
	}
}
