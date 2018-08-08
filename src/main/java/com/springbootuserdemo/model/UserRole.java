package com.springbootuserdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE")
public class UserRole extends AbstractEntityLongId {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", unique = false, nullable = true)
	private UserProfile user;

	@Column(name = "ROLE")
	private UserRoleEnum role;

	public UserRole() {
	}

	public UserRole(UserRoleEnum role) {
		this.role = role;
	}

	public UserProfile getUser() {
		return this.user;
	}

	public void setUser(UserProfile userProfile) {
		this.user = userProfile;
	}

	public UserRoleEnum getRole() {
		return role;
	}

	public void setRole(UserRoleEnum role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + (user != null ? user.getId() : "") + ", role=" + role + "]";
	}

}