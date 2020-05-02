package com.certimeter.safestadium.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.certimeter.safestadium.utilities.StringUtility;
import com.certimeter.safestadium.utilities.Utility;

@Service
public class HttpManagerService {

	static final Logger LOG = LoggerFactory.getLogger(HttpManagerService.class);
	
	
	@Value("${endpoint.backoffice.mocked}")
	boolean backofficeIsMocked;
	
	@Value("${jsonstub.user.key}")
	String jsonstubUserKey;
	@Value("${jsonstub.project.key}")
	String jsonstubProjectKey;
	
	@Value("${endpoint.backoffice.url.base}")
	String endpointBObaseUrl;
	@Value("${endpoint.jsonstub.backoffice.url.base}")
	String jsonstubBObaseUrl;
	
	
	public String send(HttpMethod httpMethod, String method, List<MediaType> mediaTypes, HashMap<String, String> mapQueryString, String accessToken){
		return send(httpMethod, method, mediaTypes, mapQueryString, accessToken, null, backofficeIsMocked);
	}
	public String send(HttpMethod httpMethod, String method, List<MediaType> mediaTypes, HashMap<String, String> mapQueryString, String accessToken, String opCode){
		return send(httpMethod, method, mediaTypes, mapQueryString, accessToken, opCode, backofficeIsMocked);
	}
	public String send(HttpMethod httpMethod, String method, List<MediaType> mediaTypes, HashMap<String, String> mapQueryString, String accessToken, String opCode, boolean useBOmocked){
		LOG.info("----------     Http Manager ["+httpMethod.toString()+"]	    ----------\t\t" + method);
		String endpoint = getBackOfficeBaseUrl( useBOmocked );
		
		endpoint += "/" + method; 
		endpoint += (mapQueryString==null || mapQueryString.isEmpty() || backofficeIsMocked) ? "" : ("?"+getQueryStringFromMap(mapQueryString));
		
		HttpHeaders httpHeader = factoryHttpHeaders(/*Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON})*/mediaTypes,accessToken, opCode, useBOmocked);
		
		LOG.info("endpoint => " + endpoint);
		
		ResponseEntity<String> response = null;
		try {
			response = (new RestTemplate()).exchange(endpoint, httpMethod, new HttpEntity<>(httpHeader), String.class);
			//LOG.info("response.getBody() => " + response.getBody());
			logResponseBody( response.getBody(), method );
		}
		catch(HttpClientErrorException httpException) {
			logException(httpException, endpoint, accessToken, opCode);
			throw httpException; 
		}
		finally {
			LOG.info("------------------------------------------------");
		}
		return response.getBody();
		
	}
	
	
	/******************************************************************************************************/
	/******************************************************************************************************/
	// [POST]				WITH JSON BODY IN REQUEST (params + json)
	/******************************************************************************************************/
	/******************************************************************************************************/
	public String sendPostWithJsonBody(String method, Object request, HashMap<String, String> mapQueryString, String accessToken, String opCode){
		return sendPostWithJsonBody(method, request, mapQueryString, accessToken, opCode, backofficeIsMocked );
	}
	
	public String sendPostWithJsonBody(String method, Object request, HashMap<String, String> mapQueryString, String accessToken, String opCode, boolean useBOmocked){
		LOG.info("----------     Http Manager [POST]     ----------\t\t" + method);
		String endpoint = getBackOfficeBaseUrl( useBOmocked );
		
		endpoint += "/" + method; 
		endpoint += (mapQueryString==null || mapQueryString.isEmpty() || backofficeIsMocked) ? "" : ("?"+getQueryStringFromMap(mapQueryString));

		HttpHeaders httpHeader = factoryHttpHeaders(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}),accessToken, opCode, useBOmocked);
		
		LOG.info("endpoint => " + endpoint);
		LOG.info("request => " + request);
		
		
		ResponseEntity<String> response = null;
		try {
			response = (new RestTemplate()).exchange(endpoint, HttpMethod.POST, new HttpEntity<>(request, httpHeader), String.class);
			//LOG.info("response.getBody() => " + response.getBody());
			logResponseBody( response.getBody(), method );
		}
		catch(HttpStatusCodeException httpException) {
			logException(httpException, endpoint, accessToken, opCode);
			throw httpException;
		}
		finally {
			LOG.info("------------------------------------------------");
		}
		
		return response.getBody();
	}
	

	
	/******************************************************************************************************/
	/******************************************************************************************************/
	// 							SEND WITH RESOURCE (file) IN RESPONSE BODY
	/******************************************************************************************************/
	/******************************************************************************************************/
	
//	public String sendForResource(HttpMethod httpMethod, String method, List<MediaType> mediaTypes, HashMap<String, String> mapQueryString, String accessToken){
//		return send(httpMethod, method, mediaTypes, mapQueryString, accessToken, null, backofficeIsMocked);
//	}
//	public String sendForResource(HttpMethod httpMethod, String method, List<MediaType> mediaTypes, HashMap<String, String> mapQueryString, String accessToken, String opCode){
//		return send(httpMethod, method, mediaTypes, mapQueryString, accessToken, opCode, backofficeIsMocked);
//	}
	public Resource sendForResource(HttpMethod httpMethod, String method, List<MediaType> mediaTypes, HashMap<String, String> mapQueryString, String accessToken, String opCode, boolean useBOmocked){
		LOG.info("----------     Http Manager ["+httpMethod.toString()+"]	    ----------\t\t" + method);
		String endpoint = method;//getBackOfficeBaseUrl( useBOmocked );
		
		//endpoint += "/" + method; 
		//endpoint += (mapQueryString==null || mapQueryString.isEmpty() || backofficeIsMocked) ? "" : ("?"+getQueryStringFromMap(mapQueryString));
		
		HttpHeaders httpHeader = factoryHttpHeaders(/*Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON})*/mediaTypes,accessToken, opCode, useBOmocked);
		
		LOG.info("endpoint => " + endpoint);
		
		ResponseEntity<Resource> response = null;
		try {
			response = (new RestTemplate()).exchange(endpoint, httpMethod, new HttpEntity<>(httpHeader), Resource.class);
			LOG.info("response.getBody() - filaname, exists? => " + response.getBody().getFilename() +", " + response.getBody().exists());
			//logResponseBody( response.getBody(), method );
		}
		catch(HttpClientErrorException httpException) {
			logException(httpException, endpoint, accessToken, opCode);
			throw httpException; 
		}
		finally {
			LOG.info("------------------------------------------------");
		}
		return response.getBody();
		
	}
	
	
	/*******************************************************************************/
	//							METODI AUSILIARI
	/*******************************************************************************/
	
	
	
	private String getQueryStringFromMap( HashMap<String, String> mapQueryString ){
		String queryString = "";
		
		for (HashMap.Entry<String, String> entry : mapQueryString.entrySet()) {
			queryString += entry.getKey() +"="+entry.getValue() + "&";
		}
		
		// SE ultimo carattere è '&' ... remove last '&'
		if( queryString.charAt(queryString.length()-1) == '&' ){	
			//"www.juvebo.com?k1=v1&k2=v2&k3=v3&" => "www.juvebo.com?k1=v1&k2=v2&k3=v3"
			queryString = queryString.substring(0, queryString.length()-1);	
		}
		
		return queryString;
	}

	private HttpHeaders factoryHttpHeaders( List<MediaType> mediaTypes, String accessToken, String opCode, boolean useBOmocked ) {
		HttpHeaders httpHeaders = new HttpHeaders();
		
		for( MediaType item : mediaTypes)
			httpHeaders.setContentType(item);
		
		if(!StringUtility.isEmptyString(accessToken)) {
			httpHeaders.set("Authorization", "Bearer " + accessToken);
			LOG.debug("HEADER[Authorization --> 'Bearer" + " " + accessToken +"']");
		}
		
		httpHeaders.set("opCode", 
			StringUtility.isEmptyString(opCode) ? UUID.randomUUID().toString() : opCode
		);
		LOG.debug("HEADER[opCode --> '" + opCode +"']");
		
		if( useBOmocked ){
			httpHeaders.set("JsonStub-User-Key", jsonstubUserKey);
			httpHeaders.set("JsonStub-Project-Key", jsonstubProjectKey);
		}
			
		return httpHeaders;
	}
	
	private String getBackOfficeBaseUrl( boolean useBOmocked ){
		return useBOmocked ? jsonstubBObaseUrl : endpointBObaseUrl;
	}
	
	
	
	/*******************************************************************************/
	//							METODI LOG HTTP EXCEPTION
	/*******************************************************************************/
	
	
	
	private static void logException(Exception exception, String endpoint, String accessToken, String opCode) {
		Integer httpStatusCode = null;
		
		if(exception instanceof HttpStatusCodeException)
			httpStatusCode = ((HttpStatusCodeException)exception).getRawStatusCode();
		else if(exception instanceof HttpClientErrorException)
			httpStatusCode = ((HttpClientErrorException)exception).getRawStatusCode();
		
		if(Utility.isNull(httpStatusCode)) {
			/***  no http exception  ***/
			LOG.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			LOG.error("@@@@@@@@@@   ["+exception.getClass().getSimpleName()+"] ERROR IN HTTP MANAGER - " + exception.getMessage());
			LOG.error("@@@@@@@@@@   accessToken => '"+ (StringUtility.isEmptyString(accessToken) ? "" : accessToken) +"'");
			LOG.error("@@@@@@@@@@   opCode => '"+ (StringUtility.isEmptyString(opCode) ? "" : opCode) +"'");
			exception.printStackTrace();
			LOG.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			
		}else if(httpStatusCode.intValue() == HttpStatus.UNAUTHORIZED.value()) {	
			/***  401  ***/
			LOG.debug("**********************************************************************************************");
			LOG.info( "**********   EXPIRED TOKEN calling '"+endpoint+"'");
			LOG.info( "**********   accessToken => '"+ (StringUtility.isEmptyString(accessToken) ? "" : accessToken) +"'");
			LOG.info( "**********   opCode => '"+ (StringUtility.isEmptyString(opCode) ? "" : opCode) +"'");
			LOG.debug("**********************************************************************************************");
			
		}else{
			/***  default  ***/
			LOG.error("@     http status code => " + httpStatusCode.intValue());
			LOG.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			LOG.error("@@@@@@@@@@   ERROR calling '"+endpoint+"' HTTP STATUS => " + httpStatusCode);
			LOG.error("@@@@@@@@@@   accessToken => '"+ (StringUtility.isEmptyString(accessToken) ? "" : accessToken) +"'");
			LOG.error("@@@@@@@@@@   opCode => '"+ (StringUtility.isEmptyString(opCode) ? "" : opCode) +"'");
			LOG.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}
	
	private static String logResponseBody(String responseBody, String method) {
		try {
//			/**********		CASO method = 'integrazioni/getAllegato/'	**********/
//			if( method.contains("integrazioni/getAllegato/") ) {
//				JSONObject responseJSONObject = new JSONObject(responseBody);
//				String content = (responseJSONObject.get("content").toString().equals("null")) ? null : responseJSONObject.getString("content");
//				if( !StringUtility.isEmptyString(content) && content.length() > 500 ) {
//					responseJSONObject.remove("content");
//					responseJSONObject.put("content", content.substring(0, 500)+"...<BASE64>");
//				}
//				LOG.info(responseJSONObject.toString());
//			}
//			
//			/**********		CASO method = '????'	**********
//			if(method.contains("????")) {
//				JSONObject data = responseJSONObject.getJSONObject("data");
//				String content = data.getString("content");
//				if( !StringUtility.isEmptyString(content) && content.length() > 500 ) {
//					data.remove("content");
//					data.put("content", content.substring(0, 500)+"...<BASE64>");
//					responseJSONObject.remove("data");
//					responseJSONObject.put("data", data);
//				}
//			}
//			***/
//			
//			/**********		DEFAULT		**********/
//			else 	
				LOG.info(responseBody);
			
		} catch(Exception e){
			LOG.error(e.getMessage());
			e.printStackTrace();
			
			LOG.info(responseBody);
		}
		
		return responseBody;
	}
	
}
