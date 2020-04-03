package com.barcode.BarcodeProject.web.UserDto;

import java.io.Serializable;

import com.barcode.BarcodeProject.model.User;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4708569114263970374L;
	private User user;
	private String token;
	
	public UserDto(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
