package com.rim.vuokrain.web;


import static com.rim.vuokrain.configuration.WebUrlsAndViews.SINGLE_ADVERT_MAP;
import static com.rim.vuokrain.configuration.WebUrlsAndViews.SINGLE_ADVERT_VIEW;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rim.vuokrain.advert.Advert;
import com.rim.vuokrain.advert.AdvertPictureUrl;
import com.rim.vuokrain.advert.AdvertService;

@Controller
public class SingleAdvertController {
			
	@Resource AdvertService advertService;
	
	@RequestMapping( value = SINGLE_ADVERT_MAP , method = RequestMethod.GET )
	public String homepageShowModels(@PathVariable("id") long id, Model model ) {		
		
		Advert advert = new Advert();		
		advert = advertService.findAdvertbyId(id);
		List<AdvertPictureUrl> pictures = advertService.findPictureUrls(id);
		model.addAttribute( "pictures", pictures );
		model.addAttribute( "ad", advert );
		return SINGLE_ADVERT_VIEW;
	}
}
