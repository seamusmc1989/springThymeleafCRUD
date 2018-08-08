package com.springbootuserdemo.repository;

import java.util.List;

import com.springbootuserdemo.model.UserRole;


public interface RoleRepositoryCustom {
	int findByUserAndRole(long userId, String role);
	List<UserRole> getRolesByUserId(long userId);
}
