package com.rim.vuokrain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rim.vuokrain.registration.RegistrationDto;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegistrationDto> {  
	
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {       
    }

	@Override
	public boolean isValid(RegistrationDto user, ConstraintValidatorContext arg1) {
		// true if matches false if dont
        return user.getPassword().equals(user.getMatchingPassword());   
	}   
    
}