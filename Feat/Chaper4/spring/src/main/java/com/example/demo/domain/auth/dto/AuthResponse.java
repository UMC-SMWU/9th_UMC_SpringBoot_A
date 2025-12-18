package com.example.demo.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthResponse {

	@Getter
	@Builder
	@AllArgsConstructor
	public static class Signup {
		private Long userId;
		private String email;
		private String name;
	}

	@Getter
	@Builder
	@AllArgsConstructor
	public static class SessionLogin {
		private Long userId;
		private String email;
		private String sessionId;
	}

	@Getter
	@Builder
	@AllArgsConstructor
	public static class JwtLogin {
		private Long userId;
		private String accessToken;
	}
}
