package com.rim.vuokrain.forgotpassword;

import com.rim.vuokrain.validation.EmailSyntax;

public class EmailDto {

	@EmailSyntax
	private String email;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	
}