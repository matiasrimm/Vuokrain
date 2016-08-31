package com.rim.vuokrain.advert;

import org.springframework.web.multipart.MultipartFile;

public class AdvertFileBacker {

	MultipartFile[] multipartFile;
	
	public MultipartFile[] getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile[] multipartFile) {
		this.multipartFile = multipartFile;
	}
}