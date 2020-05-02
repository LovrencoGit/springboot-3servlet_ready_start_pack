package com.certimeter.safestadium.enumeration;

public enum ResponseErrorEnum {
	
	ERR_1  (1, "Empty/Invalid Field"),
	ERR_2  (2, "Invalid Username Or Password"),
	ERR_3  (3, "No Result Found"),
	ERR_4  (4, "InvalidToken"),
	ERR_5  (5, "ExpiredToken"),
	ERR_6  (6, "ExceptionToken"),
	ERR_7  (7, "InvalidState"),
	ERR_8  (8, "InvalidFormatDate"),
	ERR_10 (10,"InvalidTeamNumber"),
	ERR_11 (11,"InvalidRefreshToken"),
	ERR_12 (12,"UserNotAuthorized"),
	ERR_13 (13,"GenericProblemFromBackoffice"),
	ERR_14 (14,"InvalidIdFirebase"),
	ERR_19 (19,"AppVersionOutdated"),
	
	ERR_500 (500,"UnexpectedError");

	
	private int id;
	private String description;
	
	ResponseErrorEnum(int id, String descr){
		this.id = id;
		this.description = descr;
	}

	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	/*******************************************************************/
	/*******************************************************************/
	/*******************************************************************/

	public String toString(){
		return "\nErrorEnum {\n\tid:" + id + ",\n\tdescription:" + description + "\n}";
	}
	
}
