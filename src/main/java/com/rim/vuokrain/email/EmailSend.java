package com.rim.vuokrain.email;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSend {
	
	@Resource
    private JavaMailSender javaMailSender;

	public void sendMail(String TO, String SUBJECT, String TEXT) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo( TO );
		message.setSubject( SUBJECT );
		message.setText( TEXT );
		javaMailSender.send(message);		
	}	
}
