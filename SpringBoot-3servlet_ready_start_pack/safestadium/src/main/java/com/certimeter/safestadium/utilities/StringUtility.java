package com.certimeter.safestadium.utilities;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtility {

	final static Logger LOG = LoggerFactory.getLogger(StringUtility.class);
	
	
	public static boolean isEmptyString(String text) {
		return Utility.isNull(text) || text.trim().equals("") || text.trim().length()==0 || text.trim().isEmpty();
	}
	
	@SuppressWarnings("unused")
	public static boolean isValidStringOfNumbersSplittedBy(String numbersList, String separator) {
		try {
			ArrayList<Integer> idStatoList = NumberUtility.toIntegerList(numbersList, separator);
			return true;
		}catch(Exception exc) {
			LOG.error("[ERROR] utility method failure:  NumberUtility.toIntegerList( '"+numbersList+"', '"+separator+"' )");
			return false;
		}
	}
	
	public static String toStringSplittedBy(ArrayList<Integer> numbersList, String separator) {
		String output = "";
		for(Integer n : numbersList) 		output += n + separator;
		// SE ultimo carattere è ',' 		ALLORA remove ',' at last position
		if( String.valueOf( output.charAt(output.length()-1) ).equals(separator) )		output = output.substring(0, output.length()-1);			
		return output;
	}
	
}
