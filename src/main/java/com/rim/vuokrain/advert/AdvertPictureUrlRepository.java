package com.rim.vuokrain.advert;

import java.util.List;

public interface AdvertPictureUrlRepository {

	public void insertPictureUrls(long advertId, List<String> pictureUrls);

	public List<AdvertPictureUrl> findAll();

	public List<AdvertPictureUrl> findByAdvertId(long advertId);
}