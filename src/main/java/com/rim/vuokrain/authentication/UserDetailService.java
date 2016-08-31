package com.rim.vuokrain.authentication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rim.vuokrain.registration.RegistrationService;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Resource RegistrationService registrationService;
	
	@Override
	public UserDetails loadUserByUsername(String formUsername) {
		
		// UserDetailsService.User
		boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
		// find user by String inserted in the form
        // two User -objects, one from registration and one from userDetailsService
        com.rim.vuokrain.registration.user.User user = registrationService.findByUsername(formUsername);
		
		// custom exception if no user found
		if (user == null) {
            throw new UsernameNotFoundException("No user found sorry.");
		}
				
		// if user disabled, give an option to resend verification
		// by default errors are printed to the login form
		// TODO find a better way to do this
		if (!(user.getEnabled())) {	
			throw new DisabledException( "User not enabled. Resend <a href=\"/vuokrain/resendRegToken?email="
					+ user.getUsername() 
					+ "\">verification-email</a>" );
		}
		
		// get authorities-list for current user
		List<String> authoritiesList = registrationService.findAuthoritiesListByUsername(user.getUsername());
		
		return new User(user.getUsername(),
				user.getPassword(),
				user.getEnabled(),
				accountNonExpired,
				credentialsNonExpired,
				accountNonLocked,
				getUserAuthorities(authoritiesList));
	}
	
	private List<GrantedAuthority> getUserAuthorities(List<String> authorities) {
		
		// from database to SimpleGrantedAuthorityHashSet to <GrantedAuthority>Arraylist
		Set<GrantedAuthority> grantedAuths = new HashSet<GrantedAuthority>();
		
		// build users authorities
		for (String authority : authorities) {		
			grantedAuths.add(new SimpleGrantedAuthority(authority));
		}	
		// add SimpleGrantedAuthorities
		List<GrantedAuthority> Roles = new ArrayList<GrantedAuthority>(grantedAuths);
		
		return Roles;		
	}
}

