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
import com.rim.vuokrain.forgotpassword.passwordtoken.PasswordToken;
import com.rim.vuokrain.forgotpassword.passwordtoken.PasswordTokenService;
import com.rim.vuokrain.utilities.Utilities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static com.rim.vuokrain.configuration.AppConfiguration.TEST_PROFILE;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles( TEST_PROFILE )
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class PasswordServiceTests {
	
	private String TEST_USERNAME = "teodore@testaaja.fi";
	private String TEST_TOKEN = "32870e41-4334-4640-9e84-408df5ad6c6b";	
	private String RANDOM_TOKEN = "thisIsCompletelyRandom";
	
	@Resource PasswordTokenService passwordTokenService;
	
	@Test
	public void shouldFindTokenByUsername() {
		PasswordToken passwordToken = passwordTokenService.findTokenByUsername( TEST_USERNAME );
		assertThat(passwordToken.getUsername()).isEqualTo( TEST_USERNAME );
	}
    
	@Test
	public void shouldFindTokenByToken() {
		PasswordToken passwordToken = passwordTokenService.findTokenByToken( TEST_TOKEN );
		assertThat(passwordToken.getToken()).isEqualTo( TEST_TOKEN );
	}
	
	@Test
    @Transactional    
	public void shouldInsertToken() {  
    	List<PasswordToken> tokenList = passwordTokenService.findAllTokens();
        int found = tokenList.size();
		
        PasswordToken passwordToken = new PasswordToken();
        passwordToken.setUsername( TEST_USERNAME );
        passwordToken.setExpiryDate( Utilities.getCurrentDate() );
        passwordToken.setToken( RANDOM_TOKEN );
                
        passwordTokenService.insertThisToken( passwordToken );	
				
        tokenList = passwordTokenService.findAllTokens();        
		assertThat( tokenList.size()).isEqualTo( found + 1 );
    }	
}