package com.springbootuserdemo.repository;

import java.util.List;

import com.springbootuserdemo.model.UserProfile;

public interface UserProfileRepositoryCustom {

	List<UserProfile> findAllByActive();
}
