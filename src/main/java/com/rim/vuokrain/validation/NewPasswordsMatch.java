package com.rim.vuokrain.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static com.rim.vuokrain.configuration.AppConfiguration.NEWPASSWORD_VALIDATION_ERROR;


//https://docs.jboss.org/hibernate/validator/4.1/reference/en-US/html/validator-customconstraints.html
//TYPE makes it possible to validate the whole object

@Target( { TYPE, ANNOTATION_TYPE } )
@Retention(RUNTIME)
@Constraint(validatedBy = NewPasswordsMatchValidator.class)
@Documented
public @interface NewPasswordsMatch {   
	
    String message() default NEWPASSWORD_VALIDATION_ERROR;
    
    Class<?>[] groups() default {}; 
    
    Class<? extends Payload>[] payload() default {};
    
}