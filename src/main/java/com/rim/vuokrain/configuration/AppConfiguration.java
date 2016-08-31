package com.rim.vuokrain.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Configuration
@MultipartConfig
@ComponentScan( "com.rim.vuokrain" )
@EnableTransactionManagement
@Import( WebAppConfiguration.class )
public class AppConfiguration {

	/*
	 * Configuration
	 */			
	public static final String DEFAULT_NAMESPACE  = "com.rim.vuokrain";
		
	public static final String MESSAGESOURCE_BASENAME  = "messages";
		
	public static final String JDBC_DRIVERCLASSNAME  = "jdbc.driverClassName";
	public static final String JDBC_URL  = "jdbc.url";
	public static final String JDBC_USERNAME  = "jdbc.username";
	public static final String JDBC_PASSWORD  = "jdbc.password";
		
	public static final String HIBERNATE_HBM  = "hibernate.hbm2ddl.auto";
	public static final String HIBERNATE_HBM_VALUE  = "validate";
	public static final String HIBERNATEDIALECT  = "hibernate.dialect";
	public static final String HIBERNATEDIALECT_VALUE  = "org.hibernate.dialect.MySQL5Dialect";
	
	public static final String FLYWAY_INIT_METHOD  = "migrate";
	public static final String FLYWAY_LOCATIONS  = "db/migrations/";
	public static final String EM_DEPENDS_ON  = "flyway";

	public static final String EMAIL_HOST  = "email.host";
	public static final String EMAIL_PORT  = "email.port";
	public static final String EMAIL_USERNAME  = "email.username";
	public static final String EMAIL_PASSWORD  = "email.password";
	
	public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	public static final String MAIL_TRANSPORT_PROTOCOL_VALUE = "smtp";
	public static final String MAIL_STMP_AUTH = "mail.smtp.auth";
	public static final String MAIL_STMP_AUTH_VALUE = "true";
	public static final String MAIL_STMP_TLS = "mail.smtp.starttls.enable";
	public static final String MAIL_STMP_TLS_VALUE = "true";
	public static final String MAIL_DEBUG = "mail.debug";
	public static final String MAIL_DEBUG_VALUE ="false";
	
	public static final String PRODUCTION_PROFILE  = "prod";
	public static final String PRODUCTION_PROPS_CLASSPATH  = "classpath:prod.properties";
	public static final String TEST_PROFILE  = "test";
	public static final String TEST_PROPS_CLASSPATH  = "classpath:test.properties";
	
	/*
	 * File Upload
	 */	
	public static final String FILE_ROOT = "UPLOAD_FOLDER";	
	
	/*
	 * Validation Default Errors
	 * */
	public static final String EMAIL_VALIDATION_ERROR = "Bad Email Syntac.";
	public static final String NEWPASSWORD_VALIDATION_ERROR = "New Passwords Dont Match";
	public static final String PASSWORD_VALIDATION_ERROR = "Passwords Dont Match";
	
	@Resource
	Environment env;
		
	@Configuration
    @Profile( PRODUCTION_PROFILE )
    @PropertySource({ PRODUCTION_PROPS_CLASSPATH })
    public class prodProfileConfiguration {      }
    
	@Configuration
    @Profile( TEST_PROFILE )
    @PropertySource({ TEST_PROPS_CLASSPATH })
    public class testProfilesConfiguration {      }
	
	
	@Bean
	public CommonsMultipartResolver filterMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;		
	}
	
	@Bean
    public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
    	resource.setBasename( MESSAGESOURCE_BASENAME );
    	return resource;
	}	
	
	@Bean
	@DependsOn( EM_DEPENDS_ON )
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { DEFAULT_NAMESPACE });	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(hibernateProperties());	 
	      return em;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	 
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty( JDBC_DRIVERCLASSNAME ));
		dataSource.setUrl(env.getProperty( JDBC_URL ));
		dataSource.setUsername(env.getProperty( JDBC_USERNAME ));
		dataSource.setPassword(env.getProperty( JDBC_PASSWORD ));
		return dataSource;
	}
	 
	@Bean
	public PlatformTransactionManager transactionManager( EntityManagerFactory emf ){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( emf );	 
		return transactionManager;
	}
	 
	@Bean
	public JavaMailSender javaMailSender() {		
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(env.getProperty( EMAIL_HOST ));
		javaMailSender.setPort( Integer.parseInt(env.getProperty( EMAIL_PORT )));
		javaMailSender.setUsername(env.getProperty( EMAIL_USERNAME ));
		javaMailSender.setPassword(env.getProperty( EMAIL_PASSWORD ));
		javaMailSender.setJavaMailProperties(javaMailProperties());			
		return javaMailSender;		
	}	
	
	Properties javaMailProperties() {
        Properties properties = new Properties();
        properties.setProperty( MAIL_TRANSPORT_PROTOCOL, MAIL_TRANSPORT_PROTOCOL_VALUE );
        properties.setProperty( MAIL_STMP_AUTH, MAIL_STMP_AUTH_VALUE );
        properties.setProperty( MAIL_STMP_TLS, MAIL_STMP_TLS_VALUE );
        properties.setProperty( MAIL_DEBUG, MAIL_DEBUG_VALUE );
        return properties;
	}

	Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty( HIBERNATE_HBM , HIBERNATE_HBM_VALUE );
		properties.setProperty( HIBERNATEDIALECT , HIBERNATEDIALECT_VALUE );
		return properties;
	}
	
	@Bean( initMethod = FLYWAY_INIT_METHOD )
	Flyway flyway() {
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate( true );
		flyway.setLocations( FLYWAY_LOCATIONS );
		flyway.setDataSource( dataSource() );
		return flyway;	
	}
}