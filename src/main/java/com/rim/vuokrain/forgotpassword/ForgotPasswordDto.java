package com.rim.vuokrain.forgotpassword;

import org.hibernate.validator.constraints.NotEmpty;

import com.rim.vuokrain.validation.NewPasswordsMatch;

@NewPasswordsMatch
public class ForgotPasswordDto {

	@NotEmpty	
	private String password;	
	@NotEmpty	
	private String matchingPassword;  
	
	private String email;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMatchingPassword() {
		return matchingPassword;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
