package com.certimeter.safestadium.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DateUtility {

	public static boolean isValidMonth(int iMonth) {
		return iMonth>=1 && iMonth<=12;
	}
	
	public static HashMap<String, Integer> getComponentsMapFrom(Date date) {
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		if( Utility.isNull(date) )	{
			output.put("YEAR",  null); 
			output.put("MONTH", null); 
			output.put("DAY", null);
			output.put("HOUR", null);
			output.put("MINUTE", null); 
			output.put("SECOND", null);
			output.put("MILLISECOND", null);
		}else {
			String[] dateStringArray = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format( date ).split("-");
			output.put("YEAR",  Integer.valueOf( dateStringArray[0] )); 
			output.put("MONTH", Integer.valueOf( dateStringArray[1] )); 
			output.put("DAY", Integer.valueOf( dateStringArray[2] ));
			output.put("HOUR", Integer.valueOf( dateStringArray[3] ));
			output.put("MINUTE", Integer.valueOf( dateStringArray[4] )); 
			output.put("SECOND", Integer.valueOf( dateStringArray[5] ));
			output.put("MILLISECOND", Integer.valueOf( dateStringArray[6] ));
		}
		
		return output;
	}
	
}
