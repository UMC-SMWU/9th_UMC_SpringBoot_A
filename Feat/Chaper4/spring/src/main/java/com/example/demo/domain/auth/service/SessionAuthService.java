package com.example.demo.domain.auth.service;

import com.example.demo.domain.auth.dto.AuthRequest;
import com.example.demo.domain.auth.dto.AuthResponse;
import com.example.demo.domain.auth.exception.AuthException;
import com.example.demo.domain.auth.security.CustomUserDetails;
import com.example.demo.global.api.ErrorCode;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionAuthService {

	private final AuthenticationManager authenticationManager;

	public AuthResponse.SessionLogin login(AuthRequest.Login req, HttpServletRequest request) {
		try {
			Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
			);

			var context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(auth);
			SecurityContextHolder.setContext(context);

			HttpSession session = request.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

			CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
			return AuthResponse.SessionLogin.builder()
				.userId(principal.getUserId())
				.email(principal.getEmail())
				.sessionId(session.getId())
				.build();

		} catch (AuthenticationException e) {
			throw new AuthException(ErrorCode.INVALID_CREDENTIALS);
		}
	}

	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();
		SecurityContextHolder.clearContext();
	}
}
