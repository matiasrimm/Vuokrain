package com.rim.vuokrain.integration;

import static com.rim.vuokrain.configuration.AppConfiguration.TEST_PROFILE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.rim.vuokrain.advert.Advert;
import com.rim.vuokrain.configuration.AppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles( TEST_PROFILE )
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class AdvertSearchTests {
		
	public static final String FIELD_RUBRIC  = "rubric";
	public static final String FIELD_MESSAGE  = "messageText";
	public static final String TEST_KEYWORD  = "testiotsikko";
	
	@PersistenceContext
	private EntityManager em;
	
	private FullTextEntityManager fullTextEntityManager;
	
	@Before
    public void init() throws InterruptedException {
		fullTextEntityManager = Search.getFullTextEntityManager(em);		
		fullTextEntityManager.createIndexer().startAndWait();		
    }
	
	@Test
	@Transactional
	public void shouldFindTwoAdverts(){
		
		final QueryBuilder b = fullTextEntityManager.getSearchFactory()
			    .buildQueryBuilder().forEntity( Advert.class ).get();

		Query luceneQuery =
			    b.keyword()
			    	.fuzzy()
			    	.withPrefixLength( 0 )
			    	.onFields( FIELD_RUBRIC, FIELD_MESSAGE )
			        .matching( TEST_KEYWORD )
			        .createQuery();
		
		javax.persistence.Query fullTextQuery = 
			    fullTextEntityManager.createFullTextQuery( luceneQuery, Advert.class );

		@SuppressWarnings( "unchecked" )
		List<Advert> result = fullTextQuery.getResultList();
		assertThat(result.size()).isEqualTo( 2 );
	}

}
