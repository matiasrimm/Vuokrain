package com.rim.vuokrain.registration.authorities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

@Component
public class AuthoritiesRepositoryImpl implements AuthoritiesRepository {

	@PersistenceContext
	private EntityManager em;
			
	public void insert(Authorities authorities) {
		em.persist(authorities);
	}

	public List<String> findAuthoritiesListByUsername(String username) {
		CriteriaBuilder builder = em.getCriteriaBuilder();	
		CriteriaQuery<String> criteria = builder.createQuery( String.class );
		Root<Authorities> root = criteria.from( Authorities.class );
		criteria.multiselect( root.get( "authority" ) );
		criteria.where( builder.equal( root.get( "username" ), username ) );
		List<String> authoritiesList = em.createQuery( criteria ).getResultList();
		return authoritiesList;
	}	
	
	public List<Authorities> findAllAuthorities() {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<Authorities> criteria = builder.createQuery( Authorities.class );
		Root<Authorities> root = criteria.from( Authorities.class );
		criteria.select( root );	
		List<Authorities> resultList = em.createQuery( criteria ).getResultList();			
		return resultList;
	}
}
