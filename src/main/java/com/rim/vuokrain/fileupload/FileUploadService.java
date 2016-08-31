package com.rim.vuokrain.fileupload;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	@Resource FileUploader fileUploader;
	
	public List<String> uploadFiles (MultipartFile[] files) {
		return fileUploader.uploadFiles(files);				
	}
}
