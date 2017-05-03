package com.revature.weekone.question18;

/**
 * Contains operations on strings.
 * 
 * @author Michael Hobbs
 *
 */
public abstract class StringOperation {

	/**
	 * Checks whether a string has an uppercase character.
	 * 
	 * @param string the string
	 * @return true if the string has an uppercase character
	 */
	public abstract boolean hasUppercase(String string);
	
	/**
	 * Converts all lowercase characters in a string to uppercase.
	 * 
	 * @param string the string
	 * @return the string with all uppercase characters
	 */
	public abstract String convertToUppercase(String string);
	
	/**
	 * Adds 10 to the integer in a string and prints the result.
	 * 
	 * @param string the string
	 */
	public abstract void printAdd10(String string);
	
}
