package com.example.demo.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthRequest {

	@Getter
	@NoArgsConstructor
	public static class Signup {
		private String email;
		private String password;
		private String name;
	}

	@Getter
	@NoArgsConstructor
	public static class Login {
		private String email;
		private String password;
	}
}
