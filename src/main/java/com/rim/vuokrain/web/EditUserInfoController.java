package com.rim.vuokrain.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.rim.vuokrain.additionalUserInfo.UserInfo;
import com.rim.vuokrain.additionalUserInfo.UserInfoService;

import static com.rim.vuokrain.configuration.WebUrlsAndViews.EDITUSERINFO_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.EDITUSERINFO_VIEW;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class EditUserInfoController {
	
	@Resource UserInfoService userInfoService;

	@RequestMapping( value = EDITUSERINFO_MAP , method = RequestMethod.GET )
	public String editUserGET( Model model ) {	
		
		//Get currently logged users name
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
    	
    	// prepopulate from service
    	UserInfo infoPrepopulate = new UserInfo();	
    	infoPrepopulate = userInfoService.findCurrentUsersInfo(auth.getName());
    	
		// all ok
		model.addAttribute( "userInfo", infoPrepopulate );
		return EDITUSERINFO_VIEW;
	}
	
	@RequestMapping( value = EDITUSERINFO_MAP , method = RequestMethod.POST )
	public String editUserPOST( @ModelAttribute @Valid UserInfo userInfo,
			BindingResult bindingResult, WebRequest request, Model model ) {  
		
		// if has errors return
		if ( bindingResult.hasErrors() ) {
			return EDITUSERINFO_VIEW;
		}		
		
		// update current
		userInfoService.insert( userInfo );
		
		String SUCCESS_MESSAGE = "Information updated successfully!";
		model.addAttribute( "successMessage", SUCCESS_MESSAGE );
		return EDITUSERINFO_VIEW;
	} 
	
}
