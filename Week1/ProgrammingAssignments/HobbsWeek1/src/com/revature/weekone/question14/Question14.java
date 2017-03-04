package com.revature.weekone.question14;

import java.util.Date;

/**
 * Demonstrates the switch statement.
 * 
 * The following behaviors are executed when the following cases are reached:
 * 1. Find the square root of a number using the equivalent method in the Math class. 
 * 2. Display today's date.
 * 3. Split this string and store it in a string array: "I am learning Core Java".
 * 
 * @author Michael Hobbs
 *
 */
public class Question14 {

	/**
	 * Calculate the square root of a number.
	 * 
	 * @param n the number
	 * @return the square root of the number
	 */
	public static double sqrt(double n) {
		return Math.sqrt(n); //compute the square root
	}
	
	/**
	 * Executes certain behaviors depending on which case number has been specified.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int caseNumber = 2; //the case to be executed
		
		// execute certain behavior depending on which case number has been specified.
		switch (caseNumber) {
		case 1:
			// produce the square root of a number
			double number = 9; //the number to be square rooted
			System.out.println(Question14.sqrt(number)); //print out the square root of the number
			break;
		case 2:
			// print out the current date
			Date date = new Date(); //get the current date
			System.out.println(date); //print out the current date
			break;
		case 3:
			// split up a string and print out its words
			String statement = "I am learning Core Java"; //the string to be split up and have its words printed out individually
			String[] words = statement.split(" "); //the string's words are delimited by spaces
			for (String word : words) { //print out each word of the string
				System.out.println(word); //print out a word of the string
			}
			break;
		default:
			// unrecognized case number
			System.out.println("Invalid case number");
			break;	
		}
	}

}
