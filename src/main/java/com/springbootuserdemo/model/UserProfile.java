package com.springbootuserdemo.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USERPROFILE")
public class UserProfile extends AbstractEntityLongId {

	@NotEmpty(message = "username can't be empty")
	private String username;
	
	@NotEmpty(message = "firstName can't be empty")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotEmpty(message = "lastName can't be empty")
	@Column(name = "LAST_NAME")
	private String lastName;


	@Column(name = "TITLE")
	@NotEmpty(message = "title can't be empty")
	private String title;


	@Column(name = "USER_GROUP")
	@NotEmpty(message = "group can't be empty")
	private String group;

	@Transient
	private String rolesString;
	
	@Transient
	private String rolesStringHolder;

	@Column(name = "ACTIVE_IND")
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean activeInd;

	@Column(name = "PASSWORD")
	@NotEmpty(message = "password can't be empty")
	private String password;

	@Column(name = "CHANGE_PASSWORD_IND")
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean changePasswordInd;

	@OneToMany(mappedBy = "user", targetEntity = UserRole.class, fetch = FetchType.LAZY)
	private List<UserRole> roles;

	public UserProfile() {}
	
	public UserProfile(String username, String password, String firstName, String lastName, String title, String group) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.group = group;
	}
	
	
	public String getRolesStringHolder() {
		return rolesStringHolder;
	}

	public void setRolesStringHolder(String rolesStringHolder) {
		this.rolesStringHolder = rolesStringHolder;
	}

	public String getRolesString() {
		return rolesString;
	}

	public void setRolesString(String rolesString) {
		this.rolesString = rolesString;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean isActiveInd() {
		return this.activeInd;
	}

	public void setActiveInd(boolean activeInd) {
		this.activeInd = activeInd;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isChangePasswordInd() {
		return this.changePasswordInd;
	}

	public void setChangePasswordInd(boolean changePasswordInd) {
		this.changePasswordInd = changePasswordInd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getgroup() {
		return group;
	}

	public void setgroup(String group) {
		this.group = group;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	@Transient
	public String getName() {
		return getFirstName() + " " + getLastName();
	}
	
	@Transient
	public boolean hasRole(UserRoleEnum userRole) {
		if (this.getRoles() != null) {
			for (UserRole ur: this.getRoles()) {
				if(ur.getRole().equals(userRole)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", id=" + id + ", name=" + firstName + ", title=" + title
				+ ", group=" + group + " activeInd=" + activeInd
				+ ", changePasswordInd=" + changePasswordInd + ", lastLoginDate="
				+ ", roles=" + roles
				+ "]";
	}

	
	
}
