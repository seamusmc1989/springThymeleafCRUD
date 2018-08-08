package com.springbootuserdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbootuserdemo.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>, UserProfileRepositoryCustom {
	 
    UserProfile findByUsername(String username);
}

