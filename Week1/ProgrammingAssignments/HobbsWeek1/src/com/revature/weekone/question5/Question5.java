package com.revature.weekone.question5;

/**
 * Returns a substring.
 * 
 * It does not use any substring methods of String, StringBuilder, or StringBuffer.
 * 
 * @author Michael Hobbs
 *
 */
public class Question5 {

	/**
	 * Returns a substring of a string up to idx-1 inclusive.
	 * 
	 * @param str the original string
	 * @param idx the position within str which represents the end for the substring
	 * @return a substring of str indexed from 0 to idx-1 inclusive. 
	 */
	public static String substring(String str, int idx) {
		String substr = ""; //to be the substring
		
		for (int i = 0; i < idx; ++i) { //run through the original string from the given index until the end
			substr += str.charAt(i); //append the characters of the string as we run through it in order to produce the substring
		}
		
		return substr; //return the produced substring
	}
	
	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String str = "Hello World"; //the string to be substringed
		int idx = 9; //the index within the string which that marks the end of the substring
		
		String substr = Question5.substring(str, idx); //substring the string
		
		System.out.println("The original string: " + str); //print out the original string
		System.out.println("The substring: " + substr); //print out the substring
		
	}

}
