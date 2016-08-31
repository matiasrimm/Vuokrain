package com.rim.vuokrain.advert;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.springframework.stereotype.Component;

@Component
public class AdvertRepositoryImpl implements AdvertRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	private FullTextEntityManager fullTextEntityManager;
		
	public Advert findById(long id) {
		Advert advert = em.find(Advert.class, id);
		return advert;
	}
			
	@Transactional
	public void insert(Advert ad) {
		em.persist(ad);			
		// hibernate-search flush
		fullTextEntityManager = 
			    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		flushIndexes( fullTextEntityManager );
	}

	public List<Advert> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<Advert> criteria = builder.createQuery( Advert.class );
		Root<Advert> tokenRoot = criteria.from( Advert.class );
		criteria.select( tokenRoot );	
		List<Advert> resultList = em.createQuery( criteria ).getResultList();			
		return resultList;
	}
	
	private void flushIndexes( FullTextEntityManager fullTextEntityManager ) {
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
}
