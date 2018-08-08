package com.springbootuserdemo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springbootuserdemo.model.UserRole;

@Repository
@Transactional 
public interface RoleRepository extends JpaRepository<UserRole, Long>, RoleRepositoryCustom{

}
