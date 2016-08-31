package com.rim.vuokrain.integration.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.rim.vuokrain.configuration.AppConfiguration;
import com.rim.vuokrain.registration.verificationtoken.VerificationToken;
import com.rim.vuokrain.registration.verificationtoken.VerificationTokenService;
import com.rim.vuokrain.utilities.Utilities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static com.rim.vuokrain.configuration.AppConfiguration.TEST_PROFILE;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles( TEST_PROFILE )
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class VerificationServiceTests {
	
	private String TEST_USERNAME = "teodore@testaaja.fi";
	private String TEST_TOKEN = "3a3a2cef-7238-4e2d-9681-c4e8b682282d";	
	private String RANDOM_TOKEN = "thisIsCompletelyRandom";
	
	@Resource VerificationTokenService verificationTokenService;
	
	@Test
	public void shouldFindTokenByUsername() {
		VerificationToken verificationToken = verificationTokenService.findTokenByUsername( TEST_USERNAME );
		assertThat(verificationToken.getUsername()).isEqualTo( TEST_USERNAME );
	}
    
	@Test
	public void shouldFindTokenByToken() {
		VerificationToken verificationToken = verificationTokenService.findTokenByToken( TEST_TOKEN );
		assertThat(verificationToken.getToken()).isEqualTo( TEST_TOKEN );
	}
	
	@Test
    @Transactional    
	public void shouldInsertToken() {
    	
    	List<VerificationToken> tokenList = verificationTokenService.findAllTokens();
        int found = tokenList.size();
		
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUsername( TEST_USERNAME );
        verificationToken.setExpiryDate( Utilities.getCurrentDate() );
        verificationToken.setToken( RANDOM_TOKEN );
                
        verificationTokenService.insertThisToken( verificationToken );	
				
        tokenList = verificationTokenService.findAllTokens();        
		assertThat( tokenList.size()).isEqualTo( found + 1 );	
    }	
}