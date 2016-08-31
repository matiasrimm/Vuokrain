package com.rim.vuokrain.email;

import java.util.UUID;

import javax.annotation.Resource;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rim.vuokrain.forgotpassword.passwordtoken.PasswordToken;
import com.rim.vuokrain.forgotpassword.passwordtoken.PasswordTokenRepository;
import com.rim.vuokrain.registration.verificationtoken.VerificationToken;
import com.rim.vuokrain.registration.verificationtoken.VerificationTokenRepository;

@Service
public class EmailService {
	
	@Resource VerificationTokenRepository verificationTokenRepository;	
	@Resource PasswordTokenRepository passwordTokenRepository;	
	@Resource EmailSend emailSend;
			 
	@Transactional
	public void sendRegisterationEmail(String username, String link) {
		
		// create random token associated with username and persist data
        String token = UUID.randomUUID().toString();        
        VerificationToken verificationToken = new VerificationToken(token, username);        
        verificationTokenRepository.insert(verificationToken);
        
        // get users data       
        String recipientAddress = username;
        String subject = "Registration Confirmation ";
        String confirmationUrl = link + "/confirmreg?token=" + token;
        
        // send mail
        emailSend.sendMail(recipientAddress, subject, "http://localhost:8080" + confirmationUrl);        
	}
	
	@Transactional
	public void sendReturnPasswordEmail(String username, String link) {		
			
		// create random token associated with username and persist data
        String token = UUID.randomUUID().toString();        
        PasswordToken passwordToken = new PasswordToken(token, username);        
        passwordTokenRepository.insert(passwordToken);
        
        // get users data and       
        String recipientAddress = username;
        String subject = "Forgotten Password ";
        String confirmationUrl = link + "/confirmnewpass?token=" + token;
        
        // try send mail
        emailSend.sendMail(recipientAddress, subject, "http://localhost:8080" + confirmationUrl); 
	}
}
