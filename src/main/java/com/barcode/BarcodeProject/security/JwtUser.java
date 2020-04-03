package com.barcode.BarcodeProject.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.barcode.BarcodeProject.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1184464633865352829L;
	private final int id;
	private final String username;
	private final String password;
	private final User user ;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	
	public JwtUser(int id, String username, String password, User user,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.user = user;
		this.authorities = authorities;
		this.enabled = enabled;
	}

	@JsonIgnore
	public int getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	
}
