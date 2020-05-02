package com.certimeter.safestadium.mobile.enumeration;

public enum OperatingSystemEnum {

	ANDROID (0, "ANDROID"),
	IOS 	(1, "IOS");
	
	public int code;
	public String name;
	
	private OperatingSystemEnum(int code, String name) {
		this.code= code;
		this.name= name;
	}
	
	public String toString(){
		return "OperatingSystemEnum {\t code:" + this.code + "\t name:" + this.name + "\t}";
	}
	
	
}
