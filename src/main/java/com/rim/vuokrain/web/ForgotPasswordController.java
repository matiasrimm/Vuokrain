package com.rim.vuokrain.web;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.rim.vuokrain.email.EmailService;
import com.rim.vuokrain.forgotpassword.EmailDto;
import com.rim.vuokrain.forgotpassword.ForgotPasswordDto;
import com.rim.vuokrain.forgotpassword.passwordtoken.PasswordToken;
import com.rim.vuokrain.forgotpassword.passwordtoken.PasswordTokenService;
import com.rim.vuokrain.registration.RegistrationService;
import com.rim.vuokrain.registration.user.User;

import static com.rim.vuokrain.configuration.WebUrlsAndViews.FORGOTPASS_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.FORGOTPASS_VIEW;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.PASSWORDSENTSUCCESSFULLY_VIEW;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.CHANGEFORGOTPASS_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.CHANGEFORGOTPASS_VIEW;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.PASSWORDCHANGEDSUCCESFULLY_VIEW;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.NOSUCHTOKEN_ERROR;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.TOKENEXPIRED_ERROR;

@Controller
public class ForgotPasswordController {
	
	@Resource RegistrationService registerationService;		
	@Resource EmailService emailService;	
	@Resource PasswordTokenService passwordTokenService;	
	@Resource PasswordEncoder pEncoder;  
	
	// new password get part
	@RequestMapping( value = FORGOTPASS_MAP , method = RequestMethod.GET )
	public String forgotPasswordForm(Model model) {
		model.addAttribute( "emailDto", new EmailDto() );
		return FORGOTPASS_VIEW;
	}
		
	// new password post part
    @RequestMapping( value = FORGOTPASS_MAP , method = RequestMethod.POST )
    public String forgotPasswordFormSubmit ( @ModelAttribute @Valid EmailDto emailDto,
    		BindingResult bindingResult, Model model, WebRequest request, Errors errors ) {
    	
    	if (bindingResult.hasErrors()) {
            return FORGOTPASS_VIEW;
        }
    	
        // try add to db, if no username return error    	
    	User user = registerationService.findByUsername(emailDto.getEmail());
    	
    	if(user == null) {
    		errors.rejectValue( "email", "NotFound.emailDto.email" );
            return FORGOTPASS_VIEW;  
    	}
    	
    	// try to send return pass email 
    	try {
    		emailService.sendReturnPasswordEmail(
    				emailDto.getEmail(),
    				request.getContextPath());		
        } catch (Exception e) {        	
            errors.rejectValue( "email", "EmailError.emailDto.email" );
            return FORGOTPASS_VIEW;            
        }
    
    	// if all good the return password sent success        
        return PASSWORDSENTSUCCESSFULLY_VIEW;
    }  
    
    // new pass gets confirmed by token sent to email
    @RequestMapping( value = CHANGEFORGOTPASS_MAP , method = RequestMethod.GET )
    public String confirmPasswordChange ( WebRequest request, Model model, @RequestParam("token") String token ) {
    	
    	PasswordToken passwordToken;
    	
    	// try to get token from url if doesnt work return to error page
    	try {
    		passwordToken = passwordTokenService.findTokenByToken(token);
    	} catch (NoResultException e) {
    		return NOSUCHTOKEN_ERROR;
    	}
    	
    	// check if token expired, expiration time 24 hours
    	Calendar cal = Calendar.getInstance();
    	long EXPIRATION_TIME = 24*60*60*1000;    	    	
    	long DIFFERENCE = cal.getTime().getTime() - passwordToken.getExpiryDate().getTime();    	   	
    	
    	if(DIFFERENCE >= EXPIRATION_TIME){  
    		return TOKENEXPIRED_ERROR;    		
    	}
    	    	
    	// create new dto set email to the dto
    	// email then gets forwarded in a hidden value
    	ForgotPasswordDto changePasswordDto =  new ForgotPasswordDto();
    	changePasswordDto.setEmail(passwordToken.getUsername());
    	
    	model.addAttribute("passwordDto", changePasswordDto);
    	return CHANGEFORGOTPASS_VIEW;
    }
    
    @RequestMapping(value = CHANGEFORGOTPASS_MAP , method=RequestMethod.POST)
    public String confirmPasswordChangeSubmit(@ModelAttribute @Valid ForgotPasswordDto changePasswordDto, BindingResult bindingResult, Model model, WebRequest request,Errors errors) {
    		
    	if (bindingResult.hasErrors()) {
            return CHANGEFORGOTPASS_MAP;
        }
    	
    	// get user and change pass
    	User user = registerationService.findByUsername(changePasswordDto.getEmail());
    	user.setPassword(pEncoder.encode(changePasswordDto.getPassword()));
    	registerationService.updateUser(user);
    	
    	// if all good the return password changed success        
        return PASSWORDCHANGEDSUCCESFULLY_VIEW;
    } 
}

