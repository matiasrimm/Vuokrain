package com.rim.vuokrain.integration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.rim.vuokrain.configuration.AppConfiguration;
import com.rim.vuokrain.registration.user.User;
import com.rim.vuokrain.registration.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static com.rim.vuokrain.configuration.AppConfiguration.TEST_PROFILE;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles( TEST_PROFILE )
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class UserRepositoryTests {
	
	private String TEST_USERNAME = "teodore@testaaja.fi";	
	
	private String TEST_INSERT_USERNAME = "insert@testaaja.fi";
	private String TEST_INSERT_PASSWORD = "passw";
	
	private String TEST_UPDATED_PASSWORD = "updated@testaaja.fi";
	
	@Resource UserRepository userRepository;
	
	@Test
	public void shouldFindUsersByUsername() {
		User user = userRepository.findByUsername( TEST_USERNAME );
		assertThat(user.getUsername()).isEqualTo( TEST_USERNAME );
	}
	
	@Test
	@Transactional
	public void shouldInsertUsername() {		
		List<User> usersList = userRepository.findAllUsers();
        int found = usersList.size();
        
		User user = new User();		
		
		user.setUsername( TEST_INSERT_USERNAME );
		user.setPassword( TEST_INSERT_PASSWORD );
		user.setEnabled( true );
		userRepository.insert(user);
				
        usersList = userRepository.findAllUsers();        
		assertThat( usersList.size()).isEqualTo( found + 1 );
	}
	
	@Test
	@Transactional
	public void shouldUpdateUser() {
		User user = userRepository.findByUsername( TEST_USERNAME );
		
		user.setPassword( TEST_UPDATED_PASSWORD );
		userRepository.update(user);
		
		user = userRepository.findByUsername( TEST_USERNAME );       
		assertThat( user.getPassword()).isEqualTo( TEST_UPDATED_PASSWORD );
	}
}