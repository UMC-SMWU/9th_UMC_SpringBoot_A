package com.example.demo.domain.auth.security.jwt;

import com.example.demo.domain.auth.security.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;
	private final CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, FilterChain filterChain)
		throws ServletException, java.io.IOException {

		String auth = request.getHeader("Authorization");
		if (auth != null && auth.startsWith("Bearer ")) {
			String token = auth.substring(7);
			try {
				Jws<Claims> parsed = jwtTokenProvider.parse(token);
				String email = parsed.getPayload().getSubject();

				var userDetails = userDetailsService.loadUserByUsername(email);
				var authentication = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities()
				);
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (JwtException e) {
				SecurityContextHolder.clearContext();
			}
		}

		filterChain.doFilter(request, response);
	}
}
