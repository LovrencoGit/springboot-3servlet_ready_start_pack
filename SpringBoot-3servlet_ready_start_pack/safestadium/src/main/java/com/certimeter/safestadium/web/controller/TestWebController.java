package com.certimeter.safestadium.web.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certimeter.safestadium.web.response.Response;

@RestController
public class TestWebController {

	final Logger LOG = LoggerFactory.getLogger(TestWebController.class);
	
	@GetMapping("/test")
	public ResponseEntity<Response> getBackofficeTest(){
		String now = new Date().toString();
		LOG.info("WEB TEST - " + now);
		
		return ResponseEntity.ok().body( 
			new Response(0,"SUCCESS /web/test", now, null)
		);
	}
	
}
