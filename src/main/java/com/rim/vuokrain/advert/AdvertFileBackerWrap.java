package com.rim.vuokrain.advert;

import javax.validation.Valid;

public class AdvertFileBackerWrap {
	
	@Valid
	Advert advert;
	@Valid
	AdvertFileBacker fileBacker;
	
	public Advert getAdvert() {
		return advert;
	}
	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
	
	public AdvertFileBacker getFileBacker() {
		return fileBacker;
	}
	public void setFileBacker(AdvertFileBacker fileBacker) {
		this.fileBacker = fileBacker;
	}
}
