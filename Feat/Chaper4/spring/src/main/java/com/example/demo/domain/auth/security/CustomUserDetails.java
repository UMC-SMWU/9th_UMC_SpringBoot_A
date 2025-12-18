package com.example.demo.domain.auth.security;

import com.example.demo.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

	private final Long userId;
	private final String email;
	private final String password;
	private final List<GrantedAuthority> authorities;

	public CustomUserDetails(User user) {
		this.userId = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override public String getUsername() { return email; }
	@Override public String getPassword() { return password; }
	@Override public List<GrantedAuthority> getAuthorities() { return authorities; }

	@Override public boolean isAccountNonExpired() { return true; }
	@Override public boolean isAccountNonLocked() { return true; }
	@Override public boolean isCredentialsNonExpired() { return true; }
	@Override public boolean isEnabled() { return true; }
}
