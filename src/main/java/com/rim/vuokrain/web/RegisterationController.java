package com.rim.vuokrain.web;


import java.util.Calendar;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.validation.Valid;

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
import com.rim.vuokrain.exception.DuplicateUsernameException;
import com.rim.vuokrain.registration.RegistrationDto;
import com.rim.vuokrain.registration.RegistrationService;
import com.rim.vuokrain.registration.user.User;
import com.rim.vuokrain.registration.verificationtoken.VerificationToken;
import com.rim.vuokrain.registration.verificationtoken.VerificationTokenService;

import static com.rim.vuokrain.configuration.WebUrlsAndViews.REGISTERATION_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.REGISTERATION_VIEW;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.REGISTERATIONEMAILSENT_VIEW;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.CONFIRMREGISTERATION_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.THANKSFORREGISTERATION_VIEW;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.NOSUCHTOKEN_ERROR;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.TOKENEXPIRED_ERROR;

@Controller
public class RegisterationController {

	@Resource RegistrationService usersService;		
	@Resource EmailService emailService;	
	@Resource VerificationTokenService verificationService;
		
	// registeration-form GET -part
	@RequestMapping( value = REGISTERATION_MAP , method = RequestMethod.GET )
    public String registerationForm( Model model ) {		
        model.addAttribute( "userRegisterDto", new RegistrationDto() );
        return REGISTERATION_VIEW;
    }
	
	// registeration-form POST -part
    @RequestMapping( value = REGISTERATION_MAP , method = RequestMethod.POST )
    public String registerationFormSubmit( @Valid @ModelAttribute("userRegisterDto")RegistrationDto userRegisterDto,
    		BindingResult bindingResult, Model model, WebRequest request, Errors errors ) {
    	
    	if (bindingResult.hasErrors()) {
            return REGISTERATION_VIEW;
        }
    	
        // try add to db, if duplicate username catch error
    	try {
    		usersService.insert(userRegisterDto);    		
        } catch (DuplicateUsernameException e) {        	
            errors.rejectValue( "username", "Duplicate.userRegisterDto.username" );
            return REGISTERATION_VIEW;            
        }
    	
    	// try to send registeration email to finally enable account, if fails catch error
    	try {
    		emailService.sendRegisterationEmail(
    				userRegisterDto.getUsername(),
    				request.getContextPath());		
        } catch (Exception e) {        	
            errors.rejectValue( "username", "EmailError.userRegisterDto.username" );
            return REGISTERATION_VIEW;            
        }
    	
    	// if all passes, bind dto to model and return email sent result
        model.addAttribute( "userRegisterDto", userRegisterDto );
        return REGISTERATIONEMAILSENT_VIEW;
    }  
	
    // registeration gets confirmed by token send to email
    @RequestMapping( value = CONFIRMREGISTERATION_MAP , method = RequestMethod.GET )
    public String confirmRegister ( WebRequest request, Model model, @RequestParam("token") String token ) {
    	
    	VerificationToken verificationToken;
    	
    	// try to get token from url if doesnt work return to error page
    	try {
    		verificationToken = verificationService.findTokenByToken(token);
    	} catch (NoResultException e) {
    		return NOSUCHTOKEN_ERROR;
    	}
    	
    	// check if token expired, expiration time 24 hours
    	Calendar cal = Calendar.getInstance();
    	long EXPIRATION_TIME = 24*60*60*1000;    	    	
    	long DIFFERENCE = cal.getTime().getTime() - verificationToken.getExpiryDate().getTime();    	   	
    	
    	if(DIFFERENCE >= EXPIRATION_TIME){  
    		return TOKENEXPIRED_ERROR;    		
    	}
    	
    	// if everything ok enable and after persist user
    	User user = usersService.findByUsername(verificationToken.getUsername());
    	user.setEnabled(true);    	
    	usersService.updateUser(user);
    	
    	return THANKSFORREGISTERATION_VIEW;
    }
}

