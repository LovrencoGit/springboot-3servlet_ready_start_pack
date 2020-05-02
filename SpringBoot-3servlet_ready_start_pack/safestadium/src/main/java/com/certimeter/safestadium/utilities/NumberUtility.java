package com.certimeter.safestadium.utilities;

import java.util.ArrayList;
import java.util.Arrays;

public class NumberUtility {

	public static boolean isNegative(int n) {
		return n < 0;
	}
	public static boolean isPositive(int n) {
		return n > 0;
	}
	public static boolean isNegative(double n) {
		return n < 0;
	}
	public static boolean isPositive(double n) {
		return n > 0;
	}
	public static boolean isNegative(float n) {
		return n < 0;
	}
	public static boolean isPositive(float n) {
		return n > 0;
	}
	public static Integer toInteger(String number) {
		return new Integer(number);
	}
	public static ArrayList<Integer> toIntegerList(String numbersList, String separator){
		ArrayList<Integer> output = new ArrayList<Integer>();
		for( String n : Arrays.asList(numbersList.split(separator)) ) 
			output.add( toInteger(n) );
		return output;
	}
}
