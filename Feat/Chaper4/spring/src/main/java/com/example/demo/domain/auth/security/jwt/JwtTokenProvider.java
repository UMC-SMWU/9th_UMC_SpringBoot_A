package com.example.demo.domain.auth.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

	private final SecretKey key;
	private final long accessExpMs;

	public JwtTokenProvider(
		@Value("${jwt.secret}") String secret,
		@Value("${jwt.access-exp-ms}") long accessExpMs
	) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.accessExpMs = accessExpMs;
	}

	public String createAccessToken(Long userId, String email) {
		long now = System.currentTimeMillis();
		return Jwts.builder()
			.subject(email)
			.claim("userId", userId)
			.issuedAt(new Date(now))
			.expiration(new Date(now + accessExpMs))
			.signWith(key)
			.compact();
	}

	public Jws<Claims> parse(String token) {
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
	}
}
