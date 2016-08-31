package com.rim.vuokrain.advertfulltextsearch;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rim.vuokrain.advert.Advert;

@Service
public class AdvertSearchService {
	
	@Resource AdvertSearch advertSearch;
	
	public List<Advert> searchAdvertsFromDatabase(String keyword){
		
		List<Advert> resultList = advertSearch.searchAdverts(keyword);		
		return resultList;				
	}
}
