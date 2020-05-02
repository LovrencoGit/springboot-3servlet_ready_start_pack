package com.certimeter.safestadium.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.certimeter.safestadium.web.response.Response;
import com.certimeter.safestadium.enumeration.ResponseErrorEnum;

@RestControllerAdvice
public class ExceptionController {

	final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
	
	
	@ExceptionHandler( HttpClientErrorException.class )
	public ResponseEntity<Response> handleHttpClientErrorException( HttpClientErrorException exception ){
		int statusCode = exception.getRawStatusCode();
		String exceptionToString = exception.toString();
		LOG.error("********************************************************************************");
		LOG.error("**********   ----------		HttpClientErrorException	----------   **********");
		LOG.error("**********   HTTP STATUS CODE => " + statusCode);
		LOG.error("**********   RESPONSE BODY => " + exception.getResponseBodyAsString());
		LOG.error("**********   EXCEPTION.toString() => " + exceptionToString);
		LOG.error("********************************************************************************");
		return ResponseEntity.status(statusCode).body(
			new Response(ResponseErrorEnum.ERR_13,"[HTTP CLIENT EXCEPTION - "+exceptionToString+"]", null)
		);
	} 
	@ExceptionHandler( HttpServerErrorException.class )
	public ResponseEntity<Response> handleHttpServerErrorException( HttpServerErrorException exception ){
		int statusCode = exception.getRawStatusCode();
		String exceptionToString = exception.toString();
		LOG.error("********************************************************************************");
		LOG.error("**********   ----------		HttpServerErrorException	----------   **********");
		LOG.error("**********   HTTP STATUS CODE => " + statusCode);
		LOG.error("**********   RESPONSE BODY => " + exception.getResponseBodyAsString());
		LOG.error("**********   EXCEPTION.toString() => " + exceptionToString);
		LOG.error("********************************************************************************");
		return ResponseEntity.status(statusCode).body(
				new Response(ResponseErrorEnum.ERR_13,"[HTTP CLIENT EXCEPTION - "+exceptionToString+"]", null)
				);
	} 
	
	
	/*****************************************************************************/
	/******************************		DEFAULT		******************************/
	/*****************************************************************************/
	
	
	@ExceptionHandler( Exception.class )
	public ResponseEntity<Response> handleException( Exception exception ){
		LOG.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		LOG.error("@@@@@@@@@@   ----------		Exception	----------");
		LOG.error("@@@@@@@@@@   MESSAGE => " + exception.getMessage());
		LOG.error("@@@@@@@@@@   __________stacktrace__________");
		exception.printStackTrace();
		LOG.error("@@@@@@@@@@   __________/stacktrace__________");
		LOG.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
			new Response(ResponseErrorEnum.ERR_500,"[UNEXPECTED ERROR (500)]", null)
		);
	} 

}
