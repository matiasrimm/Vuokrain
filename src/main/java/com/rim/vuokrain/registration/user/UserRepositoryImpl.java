package com.rim.vuokrain.registration.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
    private EntityManager em;	
	
	public void insert(User user) {
		em.persist(user);		
	}
		
	public void update(User user) {
		em.merge(user);		
	}

	@Override
	public User findByUsername(String username) {		
		User users = em.find(User.class, username);
		return users;
	}
	
	public List<User> findAllUsers() {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<User> criteria = builder.createQuery( User.class );
		Root<User> root = criteria.from( User.class );
		criteria.select( root );	
		List<User> resultList = em.createQuery( criteria ).getResultList();			
		return resultList;
	}
}