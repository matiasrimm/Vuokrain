package com.rim.vuokrain.registration.verificationtoken;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

@Component
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(VerificationToken verificationToken) {		
		em.persist(verificationToken);
	}
	
	public VerificationToken findTokenByToken(String token) {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<VerificationToken> criteria = builder.createQuery( VerificationToken.class );
		Root<VerificationToken> tokenRoot = criteria.from( VerificationToken.class );
		criteria.select( tokenRoot );		
		criteria.where( builder.equal( tokenRoot.get( "token" ), token ) );		
		VerificationToken verificationToken = em.createQuery( criteria ).getSingleResult();		
		return verificationToken;
	}
	
	public VerificationToken findTokenByUsername(String username) {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<VerificationToken> criteria = builder.createQuery( VerificationToken.class );
		Root<VerificationToken> tokenRoot = criteria.from( VerificationToken.class );
		criteria.select( tokenRoot );		
		criteria.where( builder.equal( tokenRoot.get( "username" ), username ) );		
		VerificationToken verificationToken = em.createQuery( criteria ).getSingleResult();		
		return verificationToken;
	}

	public List<VerificationToken> findAllTokens() {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<VerificationToken> criteria = builder.createQuery( VerificationToken.class );
		Root<VerificationToken> root = criteria.from( VerificationToken.class );
		criteria.select( root );	
		List<VerificationToken> resultList = em.createQuery( criteria ).getResultList();			
		return resultList;
	}
}
