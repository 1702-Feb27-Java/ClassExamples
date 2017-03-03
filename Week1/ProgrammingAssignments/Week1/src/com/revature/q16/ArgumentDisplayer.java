package com.revature.q16;
/**
 * 
 * @author Aaron Camm
 *
 */
public class ArgumentDisplayer {
	
	/**
	 * Displays the number of characters from all the strings in the argument list
	 * @param args
	 */
	public static void main(String[] args){
		
		int numberOfCharacters = 0;
		
		//gets the length of each argument
		for(String arg: args){
			numberOfCharacters += arg.length();
		}
		//prints out number of characters, and number of arguments.
		System.out.println("There are " + numberOfCharacters + " characters in the arguments sent.");
		
		
		
	}

}
