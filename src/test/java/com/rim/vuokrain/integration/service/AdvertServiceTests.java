package com.rim.vuokrain.integration.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.rim.vuokrain.advert.Advert;
import com.rim.vuokrain.advert.AdvertPictureUrl;
import com.rim.vuokrain.advert.AdvertService;
import com.rim.vuokrain.configuration.AppConfiguration;
import com.rim.vuokrain.utilities.Utilities;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.rim.vuokrain.configuration.AppConfiguration.TEST_PROFILE;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles( TEST_PROFILE )
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class AdvertServiceTests {
	
	private long TEST_ID = 1;
	private int TESTLIST_SIZE = 2;
	private long TEST_AD_ID = 2;
	private String RANDOM_STRING = "thisisarandomstring";
	private String RANDOM_FOLDERNAME = "randomFolderNameHere/696441008620124.jpg";
	private String TEST_PROVINCE = "province";
	private String TEST_MUNICIPALITY = "municipality";
	private String TEST_DEPARTMENT = "department";
	private String TEST_ADTYPE = "adType";
	private String TEST_PERSONORCOMPANY = "person";
	private String TEST_RUBRIC = "rubric";
	private String TEST_MESSAGETEXT = "messageText";
	private String TEST_ZIPCODE = "33340";
	private String TEST_PRICE = "12345";
	private String TEST_NAME = "name";
	private String TEST_EMAIL = "email";
	private String TEST_TELEPHONE = "telephone";
	private Date TEST_DATE = Utilities.getCurrentDate();
	
	@Resource AdvertService advertService;	
	
	@Test
	public void shouldFindAllAdverts() {
		List<Advert> ads = advertService.findAllAdverts();
		assertThat(ads.size()).isEqualTo( TESTLIST_SIZE );
	}

	@Test
	@Transactional
	public void shouldInsertAdvert () {
		
		List<Advert> advertList = advertService.findAllAdverts();
        int found = advertList.size();
		
        Advert ad = new Advert();		
		ad.setProvince( TEST_PROVINCE );
		ad.setMunicipality( TEST_MUNICIPALITY );
		ad.setDepartment( TEST_DEPARTMENT );
		ad.setAdType( TEST_ADTYPE );
		ad.setPersonOrCompany( TEST_PERSONORCOMPANY );
		ad.setRubric( TEST_RUBRIC );
		ad.setMessageText( TEST_MESSAGETEXT );
		ad.setZipCode( TEST_ZIPCODE );
		ad.setPrice( TEST_PRICE );
		ad.setName( TEST_NAME );
		ad.setEmail( TEST_EMAIL );
		ad.setTelephone( TEST_TELEPHONE );
		ad.setAddedDate( TEST_DATE );
        
        advertService.insertAdvert(ad);		
		
		advertList = advertService.findAllAdverts();
		assertThat(advertList.size()).isEqualTo( found + 1 );	
	}	
	
	@Test
	public void shouldFindAdvertbyId() {
		Advert ad = advertService.findAdvertbyId( TEST_ID );
		assertThat(ad.getId()).isEqualTo( TEST_ID );
	}

	@Test
	@Transactional
	public void shouldInsertPictureUrls() {
		
		List<AdvertPictureUrl> urlList = advertService.findAllAdvertPictureUrls();
        int found = urlList.size();
		
        List<String> pictureUrls = new ArrayList<String>();	
        pictureUrls.add( RANDOM_STRING );
        
        advertService.insertPictureUrls( TEST_AD_ID, pictureUrls );	
		
        urlList = advertService.findAllAdvertPictureUrls();        
		assertThat(urlList.size()).isEqualTo( found + 1 );
	}
	
	@Test
	public void shouldFindPictureUrls() {		
		List<AdvertPictureUrl> ad = advertService.findPictureUrls( 1 );
		assertThat(ad.get(0).getUrl()).isEqualTo( RANDOM_FOLDERNAME );
	}
}