package com.springbootuserdemo.repository;

import com.springbootuserdemo.model.UserRole;
import com.springbootuserdemo.model.UserRoleEnum;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RoleRepositoryImpl implements RoleRepositoryCustom{

	public static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);
	
    @PersistenceContext
    private EntityManager entityManager;
		
	public int findByUserAndRole(long userId, String role) {

	    Long longUserId = Long.valueOf(userId); 
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(UserRole.class);		
	    Conjunction myQueryConjunc = Restrictions.conjunction();
	    
	    Criterion userCriterion = Restrictions.eq("user.id", longUserId);
	    myQueryConjunc.add(userCriterion);
	    
		UserRoleEnum userEnumRole = UserRoleEnum.valueOf(role);
		Criterion roleCriterion = Restrictions.eq("role", userEnumRole);
		myQueryConjunc.add(roleCriterion);
		
		criteria.add(myQueryConjunc);
		
		return criteria.list().size();
	
	}

	
	public List<UserRole> getRolesByUserId(long userId)
	{

		Long longUserId = Long.valueOf(userId); 	
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(UserRole.class);		
		Criterion userCriterion = Restrictions.eq("user.id", longUserId);
		criteria.add(userCriterion);
		
		return criteria.list();
	}

	
}
