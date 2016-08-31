package com.rim.vuokrain.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.rim.vuokrain.email.EmailService;

import static com.rim.vuokrain.configuration.WebUrlsAndViews.RESENDREGISTERATIONTOKEN_MAP;

import javax.annotation.Resource;

import static com.rim.vuokrain.configuration.WebUrlsAndViews.REGISTERATIONTOKENRESENT_VIEW;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.EMAILFAILED_ERROR;

@Controller
public class ResendVerificationController {
	
	@Resource EmailService emailService;

	@RequestMapping( value = RESENDREGISTERATIONTOKEN_MAP , method = RequestMethod.GET )
    public String resendRegisterationToken ( WebRequest request, Model model,
    		@RequestParam("email") String emailRequestParam ) {
    	
		// try resend email catch failed send
    	try {
    		emailService.sendRegisterationEmail(
    				emailRequestParam,
    				request.getContextPath());		
        } catch (Exception e) {        	
            return EMAILFAILED_ERROR;            
        }
    	
    	return REGISTERATIONTOKENRESENT_VIEW;
    }    
}
