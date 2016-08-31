package com.rim.vuokrain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rim.vuokrain.forgotpassword.ForgotPasswordDto;

public class NewPasswordsMatchValidator implements ConstraintValidator<NewPasswordsMatch, ForgotPasswordDto> {  
	
    @Override
    public void initialize(NewPasswordsMatch constraintAnnotation) {       
    }

	@Override
	public boolean isValid(ForgotPasswordDto user, ConstraintValidatorContext arg1) {
		// true if matches false if dont
        return user.getPassword().equals(user.getMatchingPassword());   
	}   
    
}