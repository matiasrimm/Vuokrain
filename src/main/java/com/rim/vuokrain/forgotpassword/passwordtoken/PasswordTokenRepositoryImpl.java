package com.rim.vuokrain.forgotpassword.passwordtoken;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

@Component
public class PasswordTokenRepositoryImpl implements PasswordTokenRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(PasswordToken passwordToken) {		
		em.persist(passwordToken);
	}
	
	public PasswordToken findTokenByToken(String token) {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<PasswordToken> criteria = builder.createQuery( PasswordToken.class );
		Root<PasswordToken> tokenRoot = criteria.from( PasswordToken.class );
		criteria.select( tokenRoot );		
		criteria.where( builder.equal( tokenRoot.get( "token" ), token ) );		
		PasswordToken passwordToken = em.createQuery( criteria ).getSingleResult();		
		return passwordToken;
	}
	
	public PasswordToken findTokenByUsername(String username) {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<PasswordToken> criteria = builder.createQuery( PasswordToken.class );
		Root<PasswordToken> tokenRoot = criteria.from( PasswordToken.class );
		criteria.select( tokenRoot );		
		criteria.where( builder.equal( tokenRoot.get( "username" ), username ) );		
		PasswordToken passwordToken = em.createQuery( criteria ).getSingleResult();		
		return passwordToken;
	}

	public List<PasswordToken> findAllTokens() {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<PasswordToken> criteria = builder.createQuery( PasswordToken.class );
		Root<PasswordToken> root = criteria.from( PasswordToken.class );
		criteria.select( root );	
		List<PasswordToken> resultList = em.createQuery( criteria ).getResultList();			
		return resultList;
	}
}
