package com.rim.vuokrain.fileupload;

import static com.rim.vuokrain.configuration.AppConfiguration.FILE_ROOT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploader {

	public List<String> uploadFiles(MultipartFile[] filesList) {
		
		List<String> pictureUrls = new ArrayList<String>();		
		Path rootPath = Paths.get(FILE_ROOT);
		String filename = "";
				
		// create file-folder if doesn't exist
		if(!Files.exists(rootPath)) {		
			try {
				Files.createDirectory(Paths.get(FILE_ROOT));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	    for (MultipartFile file:filesList) {
	    	
	    	// create random Numeric String, prevent duplicates in filesystem  // TODO find out if there is a better way
	    	while( filename.isEmpty() || Files.exists(Paths.get(FILE_ROOT, filename)) ) {	    		
	    		filename = RandomStringUtils.randomNumeric(15) + ".jpg";	    		
	    	}
	    	
	    	// add to filesystem and save url-paths to list
	    	if (!file.isEmpty()) {	    		
	    		try {						
	    			Files.copy(file.getInputStream(), Paths.get(FILE_ROOT, filename));	    			
	    			pictureUrls.add(FILE_ROOT + "/" + filename);
	    		} catch (RuntimeException | IOException e) {
	    			//LOGGER
	    		}	    		
	    	} else {
	    		//LOGGER
	    	}	    	
	    }
	    
	    // return saved urls
		return pictureUrls;		
	}	
}