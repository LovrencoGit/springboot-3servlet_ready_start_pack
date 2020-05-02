package com.certimeter.safestadium.mobile.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certimeter.safestadium.mobile.response.Response;

@RestController
public class TestMobileController {

	final Logger LOG = LoggerFactory.getLogger(TestMobileController.class);
	
	@GetMapping("/test")
	public ResponseEntity<Response> getMobileTest(){
		String now = new Date().toString();
		LOG.info("MOBILE TEST - " + now);
		
		return ResponseEntity.ok().body( 
			new Response(0,"SUCCESS /mobile/test", now, null)
		);
	}
	
}
