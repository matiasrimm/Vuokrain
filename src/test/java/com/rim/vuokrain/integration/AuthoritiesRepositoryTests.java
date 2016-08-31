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
import com.rim.vuokrain.registration.authorities.Authorities;
import com.rim.vuokrain.registration.authorities.AuthoritiesRepository;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static com.rim.vuokrain.configuration.AppConfiguration.TEST_PROFILE;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles( TEST_PROFILE )
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class AuthoritiesRepositoryTests {
	
	private String TEST_USERNAME = "teodore@testaaja.fi";
	private String TEST_AUTHORITY = "TEST_AUTH";
	private String TEST_INSERT_AUTHORITY = "ADMIN";
		
	@Resource AuthoritiesRepository authoritiesRepository;
	
	@Test
	public void shouldFindAuthoritiesListByUsername() {
		List<String> auth_list =
				authoritiesRepository.findAuthoritiesListByUsername( TEST_USERNAME ); 
		assertThat( auth_list.get(0)).isEqualTo( TEST_AUTHORITY );
	}
	
	@Test
	@Transactional
	public void shouldInsertAuthorities() {		
		List<Authorities> authoritiesList = authoritiesRepository.findAllAuthorities();
        int found = authoritiesList.size();
        
        Authorities authorities = new Authorities();		
		
        authorities.setUsername( TEST_USERNAME );
        authorities.setAuthority( TEST_INSERT_AUTHORITY );
        
		authoritiesRepository.insert(authorities);
				
		authoritiesList = authoritiesRepository.findAllAuthorities();        
		assertThat( authoritiesList.size()).isEqualTo( found + 1 );
	}
}