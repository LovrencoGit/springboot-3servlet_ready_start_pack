package com.certimeter.safestadium.mobile.response;

import com.certimeter.safestadium.enumeration.ResponseErrorEnum;

public class Response {

	private int returnCode;
	private String errorMessage;
	private String token;
	private Payload payload;
	
	
	
	public Response(int returnCode, String errorMessage, String token, Payload payload) {
		super();
		this.returnCode = returnCode;
		this.errorMessage = errorMessage;
		this.token = token;
		this.payload = payload;
	}
	public Response(ResponseErrorEnum errorEnum , String token, Payload payload) {
		super();
		this.returnCode = errorEnum.getId();
		this.errorMessage = errorEnum.getDescription();
		this.token = token;
		this.payload = payload;
	}

	
	
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	
	
	
	@Override
	public String toString() {
		return "Response {\n\treturnCode=" + returnCode + ", \n\terrorMessage=" + errorMessage + ", \n\ttoken=" + token
				+ ", \n\tpayload=" + payload + "\n}";
	}
	
}
