package com.example.demo.domain.auth.controller;

import com.example.demo.domain.auth.dto.AuthRequest;
import com.example.demo.domain.auth.dto.AuthResponse;
import com.example.demo.domain.auth.service.AuthService;
import com.example.demo.domain.auth.service.SessionAuthService;
import com.example.demo.global.api.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/session")
public class SessionAuthController {

	private final AuthService authService;
	private final SessionAuthService sessionAuthService;


	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<AuthResponse.Signup>> signup(@RequestBody AuthRequest.Signup req) {
		AuthResponse.Signup data = authService.signup(req);
		return ResponseEntity.status(SuccessCode.SIGNUP_SUCCESS.getHttpStatus())
			.body(ApiResponse.success(SuccessCode.SIGNUP_SUCCESS, data));
	}


	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse.SessionLogin>> login(
		@RequestBody AuthRequest.Login req,
		HttpServletRequest request
	) {
		AuthResponse.SessionLogin data = sessionAuthService.login(req, request);
		return ResponseEntity.status(SuccessCode.LOGIN_SUCCESS.getHttpStatus())
			.body(ApiResponse.success(SuccessCode.LOGIN_SUCCESS, data));
	}


	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {
		sessionAuthService.logout(request);
		return ResponseEntity.status(SuccessCode.LOGOUT_SUCCESS.getHttpStatus())
			.body(ApiResponse.success(SuccessCode.LOGOUT_SUCCESS, null));
	}
}
