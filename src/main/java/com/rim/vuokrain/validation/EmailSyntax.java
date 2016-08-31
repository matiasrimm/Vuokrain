package com.rim.vuokrain.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static com.rim.vuokrain.configuration.AppConfiguration.EMAIL_VALIDATION_ERROR;

//https://docs.jboss.org/hibernate/validator/4.1/reference/en-US/html/validator-customconstraints.html

@Target( { METHOD, FIELD, ANNOTATION_TYPE } )
@Retention( RUNTIME )
@Constraint( validatedBy = EmailSyntaxValidator.class )
@Documented
public @interface EmailSyntax {   
	
    String message() default EMAIL_VALIDATION_ERROR;
    
    Class<?>[] groups() default {}; 
    
    Class<? extends Payload>[] payload() default {};
    
}
