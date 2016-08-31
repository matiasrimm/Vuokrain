package com.rim.vuokrain.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.rim.vuokrain.additionalUserInfo.UserInfo;
import com.rim.vuokrain.additionalUserInfo.UserInfoService;
import com.rim.vuokrain.advert.Advert;
import com.rim.vuokrain.advert.AdvertFileBackerWrap;
import com.rim.vuokrain.advert.AdvertService;
import com.rim.vuokrain.fileupload.FileUploadService;
import com.rim.vuokrain.utilities.Utilities;

import static com.rim.vuokrain.configuration.WebUrlsAndViews.NEWADVERT_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.NEWADVERT_VIEW;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.NEWADVERTSUCCESS_VIEW;


@Controller
public class NewAdvertController {
	
	@Resource AdvertService advertService;
	@Resource UserInfoService userInfoService;
	@Resource FileUploadService fileUploadService;					
	
	@RequestMapping( value = NEWADVERT_MAP , method = RequestMethod.GET )
	public String newAdvertForm( Model model ) {
		
		AdvertFileBackerWrap advertPrepopulateWrap = new AdvertFileBackerWrap();
		Advert advertPrepopulate = new Advert();
		String PREDEFINED_ADTYPE = "sell";
		
		// help currently logged users and set info to new advert on prefill
		// get currently logged in users username and fetch userinfo from service
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();    	
    	UserInfo userInfo = new UserInfo();	
    	userInfo = userInfoService.findCurrentUsersInfo(auth.getName());
    	
    	advertPrepopulate.setName(userInfo.getUsername()); 
    	advertPrepopulate.setEmail(userInfo.getUsername());
    	advertPrepopulate.setAdType(PREDEFINED_ADTYPE);
    	advertPrepopulate.setPersonOrCompany(userInfo.getPersonOrCompany());
    	advertPrepopulate.setProvince(userInfo.getProvince());
    	advertPrepopulate.setMunicipality(userInfo.getMunicipality());
    	advertPrepopulate.setZipCode(userInfo.getZipCode());    	
    	advertPrepopulate.setName(userInfo.getName());		
    	advertPrepopulate.setTelephone(userInfo.getTelephone());
    	    	
    	advertPrepopulateWrap.setAdvert(advertPrepopulate);
    	
		model.addAttribute( "advertFileBackerWrap", advertPrepopulateWrap );
		return NEWADVERT_VIEW;
	}
		
	// POST gets advertFileBackerWrap that contains two objects Advert and FormBacker
	// FormBacker doesn't save to database, when Advert does
	@RequestMapping( value = NEWADVERT_MAP , method = RequestMethod.POST )
	public String newAdvertFormSubmit( @ModelAttribute @Valid AdvertFileBackerWrap advertFormBackerWrap,
			BindingResult bindingResult, WebRequest request, Errors errors ) {
	    	
		MultipartFile[] files = advertFormBackerWrap.getFileBacker().getMultipartFile();		
		List<String> pictureUrls = new ArrayList<String>();		
		
		//If has errors return
		if (bindingResult.hasErrors()) {	    		
			return NEWADVERT_VIEW;
		}	
		
		// try save pictures to filesystem and return saved pictureUrls
		pictureUrls = fileUploadService.uploadFiles(files);
		
		// check if there were no pictures uploaded
		if (pictureUrls.isEmpty()) {
			errors.rejectValue( "fileBacker.multipartFile" ,
					"NoPicture.AdvertFileBackerWrap.fileBacker.multipartFile" );
			return NEWADVERT_VIEW;
		}
			    		    		    	
	    // set current date to keep on track with advert expiration and stuff
	    advertFormBackerWrap.getAdvert().setAddedDate(Utilities.getCurrentDate());
	    	
	    // add advert to database
	    advertService.insertAdvert(advertFormBackerWrap.getAdvert());
	    	
	    // pictureUrls to database
	    advertService.insertPictureUrls(advertFormBackerWrap.getAdvert().getId(), pictureUrls);
	    	    	
		// all ok
		return NEWADVERTSUCCESS_VIEW;
	}  		
}