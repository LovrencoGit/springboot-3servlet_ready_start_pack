package com.certimeter.safestadium.backoffice.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certimeter.safestadium.backoffice.response.Response;

@RestController
public class TestBackofficeController {

	final Logger LOG = LoggerFactory.getLogger(TestBackofficeController.class);
	
	@GetMapping("/test")
	public ResponseEntity<Response> getBackofficeTest(){
		String now = new Date().toString();
		LOG.info("BACKOFFICE TEST - " + now);
		
		return ResponseEntity.ok().body( 
			new Response(0,"SUCCESS /backoffice/test", now, null)
		);
	}
	
}
