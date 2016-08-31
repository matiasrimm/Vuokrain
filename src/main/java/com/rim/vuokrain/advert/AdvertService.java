package com.rim.vuokrain.advert;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class AdvertService {
	
	@Resource AdvertRepository adRepository;	
	@Resource AdvertPictureUrlRepository advertPictureRepository;
	
	public List<Advert> findAllAdverts() {
		return adRepository.findAll();
	}

	public Advert findAdvertbyId(long id) {
		return adRepository.findById(id);
	}
	
	public void insertAdvert (Advert ad) {
		adRepository.insert(ad);
	}	
		
	public List<AdvertPictureUrl> findAllAdvertPictureUrls() {
		return advertPictureRepository.findAll();
	}

	public List<AdvertPictureUrl> findPictureUrls(long advertId) {		
		return advertPictureRepository.findByAdvertId(advertId);		
	}
	
	public void insertPictureUrls(long advertId, List<String> pictureUrls) {		
		advertPictureRepository.insertPictureUrls( advertId, pictureUrls);
	}
}
