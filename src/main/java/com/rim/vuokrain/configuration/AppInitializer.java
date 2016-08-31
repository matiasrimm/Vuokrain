package com.rim.vuokrain.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.rim.vuokrain.configuration.AppConfiguration;
import static com.rim.vuokrain.configuration.AppConfiguration.PRODUCTION_PROFILE;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {		
		super.onStartup(servletContext);
		servletContext.setInitParameter("spring.profiles.active", PRODUCTION_PROFILE);
	}
}