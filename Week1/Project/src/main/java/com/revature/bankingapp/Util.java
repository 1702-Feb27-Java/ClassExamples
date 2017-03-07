package com.revature.bankingapp;

import java.util.Scanner;

public class Util {
	
	/**
	 * Gets an Integer from the scanner's nextline. 
	 * @param scan - Scanner class to check for next line.
	 * @return - Integer that is read from the next line of scanner, null if nextline is not a number
	 */
	/*public static Integer getIntegerFromScanner(Scanner scan){
		Integer result;
		
		try{
			result = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException nfe){
			result = null;
		}
		
		return result;
	}*/
	/**
	 * Gets a Double from the scanner's nextline. 
	 * @param scan - Scanner class to check for next line.
	 * @return - Integer that is read from the next line of scanner, null if nextline is not a number
	 */
	public static Double getDoubleFromScanner(Scanner scan) {
		Double result;
		
		try{
			result = Double.parseDouble(scan.nextLine());
		} catch (NumberFormatException nfe){
			result = null;
		}
		
		return result;
	}
}
