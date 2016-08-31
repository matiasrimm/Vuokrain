package com.rim.vuokrain.advert;

import java.util.List;

public interface AdvertRepository {

	public Advert findById(long id);
	
	public void insert(Advert ad);
	
	public List<Advert> findAll();
}