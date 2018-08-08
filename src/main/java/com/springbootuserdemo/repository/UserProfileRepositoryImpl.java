package com.springbootuserdemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springbootuserdemo.model.UserProfile;

public class UserProfileRepositoryImpl implements UserProfileRepositoryCustom {

	public static final Logger log = LoggerFactory.getLogger(UserProfileRepositoryImpl.class);

	@PersistenceContext
    private EntityManager entityManager;
	
	public List<UserProfile> findAllByActive()
	{
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(UserProfile.class);
		Criterion activeCriterion = Restrictions.eq("activeInd", true);
		criteria.add(activeCriterion);
		
		return criteria.list();
	}

	
}
