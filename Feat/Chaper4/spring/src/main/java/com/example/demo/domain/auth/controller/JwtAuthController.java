package com.example.demo.domain.auth.controller;

import com.example.demo.domain.auth.dto.AuthRequest;
import com.example.demo.domain.auth.dto.AuthResponse;
import com.example.demo.domain.auth.service.AuthService;
import com.example.demo.domain.auth.service.JwtAuthService;
import com.example.demo.global.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/jwt")
public class JwtAuthController {

	private final AuthService authService;
	private final JwtAuthService jwtAuthService;


	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<AuthResponse.Signup>> signup(@RequestBody AuthRequest.Signup req) {
		AuthResponse.Signup data = authService.signup(req);
		return ResponseEntity.status(SuccessCode.SIGNUP_SUCCESS.getHttpStatus())
			.body(ApiResponse.success(SuccessCode.SIGNUP_SUCCESS, data));
	}


	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse.JwtLogin>> login(@RequestBody AuthRequest.Login req) {
		AuthResponse.JwtLogin data = jwtAuthService.login(req);
		return ResponseEntity.status(SuccessCode.LOGIN_SUCCESS.getHttpStatus())
			.body(ApiResponse.success(SuccessCode.LOGIN_SUCCESS, data));
	}
}
