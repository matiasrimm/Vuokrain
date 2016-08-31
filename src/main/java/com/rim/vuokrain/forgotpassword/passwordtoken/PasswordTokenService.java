package com.rim.vuokrain.forgotpassword.passwordtoken;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class PasswordTokenService {

	@Resource PasswordTokenRepository passwordTokenRepository;    
    
	public PasswordToken findTokenByUsername(String username) {
		PasswordToken passwordToken = passwordTokenRepository.findTokenByUsername(username);		
		return passwordToken;
	}
    
    public PasswordToken findTokenByToken(String token) {
    	PasswordToken passwordToken = passwordTokenRepository.findTokenByToken(token);		
		return passwordToken;
	}      
    
    public List<PasswordToken> findAllTokens() {
    	List<PasswordToken> passwordTokenList = passwordTokenRepository.findAllTokens();		
		return passwordTokenList;
	} 
    
    @Transactional
    public void insertThisToken(PasswordToken passwordToken) {
    	passwordTokenRepository.insert(passwordToken);
    }	
}