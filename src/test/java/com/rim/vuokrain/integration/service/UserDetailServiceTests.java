package com.rim.vuokrain.integration.service;

import static com.rim.vuokrain.configuration.AppConfiguration.TEST_PROFILE;
import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.rim.vuokrain.authentication.UserDetailService;
import com.rim.vuokrain.configuration.AppConfiguration;
import com.rim.vuokrain.registration.RegistrationService;
import com.rim.vuokrain.registration.user.User;
import com.rim.vuokrain.registration.user.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles( TEST_PROFILE )
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class UserDetailServiceTests {
	
	private String TEST_USER = "teodore@testaaja.fi";
	private String NOT_EXISTING_USER = "idontexists";
	
	@Resource UserDetailService authUserService;
	@Resource RegistrationService registrationService;
	@Resource UserRepository usersRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test	
	public void shouldLoadUserByUsername(){
		UserDetails authUser = authUserService.loadUserByUsername( TEST_USER );
		assertThat(authUser.getUsername()).isEqualTo( TEST_USER );		
	}

	@Test
	@Transactional
	public void shouldThrowDisabledExcep(){
		User user = registrationService.findByUsername( TEST_USER );
		user.setEnabled( false );
		usersRepository.update( user );
		
		thrown.expect(DisabledException.class);
	    thrown.expectMessage("User not enabled. Resend <a href=\"/vuokrain/resendRegToken?email="
				+ user.getUsername() 
				+ "\">verification-email</a>");	
	    
		authUserService.loadUserByUsername( TEST_USER );			
	}
	
	@Test	
	public void shouldThrowUsernameNotFoundException(){
		thrown.expect(UsernameNotFoundException.class);
		thrown.expectMessage("No user found sorry.");
		
		authUserService.loadUserByUsername( NOT_EXISTING_USER );
	}	
}