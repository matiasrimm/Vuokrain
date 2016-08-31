package com.rim.vuokrain.advert;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class AdvertPictureUrlRepositoryImpl implements AdvertPictureUrlRepository {

	@PersistenceContext
	private EntityManager em;
			
	@Transactional
	public void insertPictureUrls(long advertId, List<String> pictureUrls) {
		// save picture urls associated with a single advert
		AdvertPictureUrl advertPictureUrl = new AdvertPictureUrl();		
		for (String url:pictureUrls) {
			advertPictureUrl.setAdvertid(advertId);
			advertPictureUrl.setUrl(url);
			em.merge(advertPictureUrl);
	    }		
	}
	
	public List<AdvertPictureUrl> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();		
		CriteriaQuery<AdvertPictureUrl> criteria = builder.createQuery( AdvertPictureUrl.class );
		Root<AdvertPictureUrl> tokenRoot = criteria.from( AdvertPictureUrl.class );
		criteria.select( tokenRoot );	
		List<AdvertPictureUrl> resultList = em.createQuery( criteria ).getResultList();			
		return resultList;
	}
	
	public List<AdvertPictureUrl> findByAdvertId(long advertId) {		
		CriteriaBuilder builder = em.getCriteriaBuilder();	
		CriteriaQuery<AdvertPictureUrl> criteria = builder.createQuery( AdvertPictureUrl.class );
		Root<AdvertPictureUrl> root = criteria.from( AdvertPictureUrl.class );
		criteria.select( root );
		criteria.where( builder.equal( root.get( "advertid" ), advertId ) );		
		List<AdvertPictureUrl> authoritiesList = em.createQuery( criteria ).getResultList();
		return authoritiesList;
	}	
}
