package com.barcode.BarcodeProject.model;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name = "users")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;

	@Column(name="name")
	private String name;

	@Column(name="lastName")
	private String lastName;

	@Column(name="email")
	private String email;

	@Column(name="address")
	private String address;

	@Column(name="score")
	private int score;

	@Column(name="password")
	private String password;

	@Column(name="enabled")
	private boolean enabled;

	@Column(name="role")
	private String role;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", address=");
		builder.append(address);
		builder.append(", score=");
		builder.append(score);
		builder.append(", password=");
		builder.append(password);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}
}
