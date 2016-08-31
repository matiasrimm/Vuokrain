package com.rim.vuokrain.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rim.vuokrain.authentication.UserDetailService;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.HOME_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.SINGLE_ADVERT_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.LOGIN_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.LOGOUT_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.FORGOTPASS_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.CHANGEFORGOTPASS_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.REGISTERATION_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.CONFIRMREGISTERATION_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.RESENDREGISTERATIONTOKEN_MAP;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired	
	private UserDetailService authService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers( HOME_MAP, SINGLE_ADVERT_MAP,
                		FORGOTPASS_MAP, CHANGEFORGOTPASS_MAP,
                		REGISTERATION_MAP, CONFIRMREGISTERATION_MAP,
                		RESENDREGISTERATIONTOKEN_MAP ).permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .permitAll()
                .and()
            .logout()
            	.logoutUrl( LOGOUT_MAP )
            	.logoutSuccessUrl( LOGIN_MAP )
                .permitAll();
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {	
		auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
