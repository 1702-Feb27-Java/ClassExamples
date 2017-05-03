package com.revature.weekone.question3;

/**
 * Reverses a string.
 * 
 * It does not use a temporary variable nor the reverse() method in either StringBuffer or StringBuilder.
 * 
 * @author Michael Hobbs
 *
 */
public class Question3 {
	
	/**
	 * Reverses a string.
	 * 
	 * @param string the string to be reversed
	 * @return the reverse of the string
	 */
	public static String reverse(String string) {
		int originalLength = string.length(); //remember the original length because we will be appending to the string in the process of reversing
		
		for (int i = originalLength - 1; i >= 0; i--) { //step backwards through the original string
			string += string.charAt(i); //append characters as we step backward through the string in order to produce the reverse of the string
		}
		
		return string.substring(originalLength); //get the appended portion of the string that represents the reverse of the original string 
	}
	
	/**
	 * Reverses a string.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String string = "Hello World"; //the string to be reversed
		
		System.out.println("The original string: " + string); //print out the original string
		
		string = Question3.reverse(string); //reverse the string
		
		System.out.println("The reversed string: " + string); //print out the reversed string
		
	}

}
