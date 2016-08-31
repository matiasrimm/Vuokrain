package com.rim.vuokrain.forgotpassword.passwordtoken;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.rim.vuokrain.utilities.Utilities;

@Entity
public class PasswordToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	    
	private String token;
	private String username;
	private Date expiryDate;
	
	public PasswordToken() {}
	
	public PasswordToken(String token, String username) {
        super();
        this.token = token;
        this.username = username;
        this.expiryDate = Utilities.getCurrentDate();        
    }
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
