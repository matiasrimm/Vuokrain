package com.rim.vuokrain.advertfulltextsearch;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rim.vuokrain.advert.Advert;

@Component
public class AdvertSearch {
	/*
	 * Fields to be searched maintainability
	 * */	
	public static final String FIELD_RUBRIC  = "rubric";
	public static final String FIELD_MESSAGE  = "messageText";
	
	@PersistenceContext
	private EntityManager em;
		
	@Transactional
	public List<Advert> searchAdverts(String keyword){
				
		FullTextEntityManager fullTextEntityManager = 
			    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);	
		
			final QueryBuilder b = fullTextEntityManager.getSearchFactory()
			    .buildQueryBuilder().forEntity( Advert.class ).get();

			Query luceneQuery =
			    b.keyword()
			    	.fuzzy()
			    	.withPrefixLength( 0 )
			    	.onFields( FIELD_RUBRIC, FIELD_MESSAGE )
			        .matching( keyword )
			        .createQuery();
			javax.persistence.Query fullTextQuery = 
			    fullTextEntityManager.createFullTextQuery( luceneQuery, Advert.class );

			@SuppressWarnings("unchecked")
			List<Advert> result = fullTextQuery.getResultList();
			return result;			
	}
}
