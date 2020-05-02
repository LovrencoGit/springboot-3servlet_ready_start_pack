package com.certimeter.safestadium.utilities;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class FileSystemUtility {

	private static final Logger LOG = LoggerFactory.getLogger(FileSystemUtility.class);
	
	public static FileSystemResource getFileSystemResourceFromFilesystem(ResourceLoader resourceLoader, String fullPath){
		LOG.info("fullPath => " + fullPath);
    	Resource resource = resourceLoader.getResource("file:"+fullPath);
    	File output = null;
    	try {
    		output = resource.getFile();
    	} catch (IOException e) {
    		LOG.error("[ERROR - FILE NOT FOUND] " + fullPath);
    		LOG.error("[UNEXPECTED ERROR] " + e.getMessage());
    		e.printStackTrace();
    	}
    	return new FileSystemResource(output);
    }
	
}
