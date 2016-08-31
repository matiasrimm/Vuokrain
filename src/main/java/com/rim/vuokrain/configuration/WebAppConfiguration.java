package com.rim.vuokrain.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebAppConfiguration extends WebMvcConfigurerAdapter {
	
	/*
	 * Configurations
	 */
	public static final String VIEWRESOLVER_PREFIX  = "/WEB-INF/views/";
	public static final String VIEWRESOLVER_SUFFIX  = ".jsp";	

	public static final String RESOURCE_HANDLER  = "/resources/**";
	public static final String RESOURCE_HANDLER_LOCATION  = "/resources/";
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix( VIEWRESOLVER_PREFIX );
		viewResolver.setSuffix( VIEWRESOLVER_SUFFIX );		
		return viewResolver;
	}	
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler( RESOURCE_HANDLER ).addResourceLocations( RESOURCE_HANDLER_LOCATION );
	}	
}
