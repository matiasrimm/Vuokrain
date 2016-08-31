package com.rim.vuokrain.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.rim.vuokrain.additionalUserInfo.UserInfoService;
import com.rim.vuokrain.advert.Advert;
import com.rim.vuokrain.advert.AdvertService;
import com.rim.vuokrain.advertfulltextsearch.AdvertSearchService;

import static com.rim.vuokrain.configuration.WebUrlsAndViews.HOME_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.HOME_VIEW;

@Controller
public class HomePageController {
			
	@Resource AdvertService advertService;
	@Resource AdvertSearchService advertSearchService;
	@Resource UserInfoService userInfoService;	
		
	@RequestMapping( value = HOME_MAP , method = RequestMethod.GET )
	public String homepageShowModels( Model model ) {	
				
		List<Advert> frontpageAdvertList = new ArrayList<Advert>();
		frontpageAdvertList = advertService.findAllAdverts();
		
		model.addAttribute( "adList", frontpageAdvertList );
		return HOME_VIEW;
	}
	
	@RequestMapping( value = HOME_MAP , method = RequestMethod.POST )
	public String homepageSearchForModels( @RequestParam("s") String searchString,
			WebRequest request, Model model ) {		
		
		List<Advert> searchedAdList = new ArrayList<Advert>();
		searchedAdList = advertSearchService.searchAdvertsFromDatabase( searchString );
				
		model.addAttribute( "adList", searchedAdList );
		return HOME_VIEW;
	}
}
