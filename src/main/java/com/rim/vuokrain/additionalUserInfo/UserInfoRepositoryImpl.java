package com.rim.vuokrain.additionalUserInfo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class UserInfoRepositoryImpl implements UserInfoRepository {
 
	@PersistenceContext
	private EntityManager em;
			
	@Transactional
	public void insert(UserInfo userInfo) {
		em.merge(userInfo);
	}
	
	public UserInfo findByUsername(String username) {
		UserInfo userInfo = em.find(UserInfo.class, username);
		return userInfo;
	}

	public List<UserInfo> findAllUserInfo() {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<UserInfo> criteria = builder.createQuery( UserInfo.class );
		Root<UserInfo> root = criteria.from( UserInfo.class );
		criteria.select( root );	
		List<UserInfo> resultList = em.createQuery( criteria ).getResultList();			
		return resultList;
	}
}
