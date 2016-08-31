package com.rim.vuokrain.registration.verificationtoken;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {

	@Resource VerificationTokenRepository verificationTokenRepository;
    
    // verification token stuff, get & create tokens
    public VerificationToken findTokenByUsername(String username) {
    	VerificationToken verificationToken = verificationTokenRepository.findTokenByUsername(username);		
		return verificationToken;
	}
    
    public VerificationToken findTokenByToken(String token) {
    	VerificationToken verificationToken = verificationTokenRepository.findTokenByToken(token);		
		return verificationToken;
	} 
    
    public List<VerificationToken> findAllTokens() {
    	List<VerificationToken> verificationTokenList = verificationTokenRepository.findAllTokens();		
		return verificationTokenList;
	} 
    
    @Transactional
    public void insertThisToken(VerificationToken verificationToken) {
    	verificationTokenRepository.insert(verificationToken);
    }
}
