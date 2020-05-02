package com.certimeter.safestadium.pojo;

/*
CREATE TABLE "DEVICE" 
(	
	"ID_DEVICE" 					VARCHAR2(50 BYTE)		-- primary key
	"USERNAME" 						VARCHAR2(200 BYTE)
	"FIREBASE_REGISTRATION_TOKEN" 	VARCHAR2(300 BYTE), 
	"OPERATING_SYSTEM" 				VARCHAR2(1024 BYTE),
	"LOGIN_TIMESTAMP" 				TIMESTAMP (6) 			-- only for database reporting, not in pojo object 
)
*/

public class Device {

	private String idDevice;	/** primary key **/
	private String username;
	private String firebaseRegistrationToken;
	private String operatingSystem;
	
	public Device() {
	}

	public String getIdDevice() {
		return idDevice;
	}
	public void setIdDevice(String idDevice) {
		this.idDevice = idDevice;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirebaseRegistrationToken() {
		return firebaseRegistrationToken;
	}
	public void setFirebaseRegistrationToken(String firebaseRegistrationToken) {
		this.firebaseRegistrationToken = firebaseRegistrationToken;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	
	@Override
	public String toString() {
		return "User2Device {\n\tidDevice=" + idDevice + ", \n\tusername=" + username
				+ ", \n\tfirebaseRegistrationToken=" + firebaseRegistrationToken + ", \n\toperatingSystem="
				+ operatingSystem + "\n}";
	}
	
}
