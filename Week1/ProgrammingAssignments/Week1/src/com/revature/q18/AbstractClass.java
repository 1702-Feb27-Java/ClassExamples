package com.revature.q18;
/**
 * 
 * @author Aaron Camm
 *
 */
public abstract class AbstractClass {

	/**
	 * Determines if a String s has an uppercase character
	 * @param s 
	 * @return true if s has an uppercase character, false other.
	 */
	public abstract boolean hasUppercaseCharacters(String s);
	
	
	/**
	 * Returns the s string with all the lowercase characters turned into their uppercase equivalents.
	 * @param s
	 * @return
	 */
	public abstract String convertToUppercase(String s);
	
	
	/**
	 * Given a string with a number, print out the number plus 10 to the console
	 * 
	 *  @param s A string with an integer in it
	 */
	public abstract void Add10ToInputStringAndPrint(String s);
}
