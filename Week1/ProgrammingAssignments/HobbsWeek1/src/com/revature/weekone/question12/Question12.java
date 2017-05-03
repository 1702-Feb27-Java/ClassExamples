package com.revature.weekone.question12;

/**
 * Prints out even numbers from an array.
 * 
 * It uses an enhanced FOR loop in doing so.
 * 
 * @author Michael Hobbs
 *
 */
public class Question12 {
	
	/**
	 * Determines whether a number is even.
	 * 
	 * @param n the number
	 * @return true if the number is even
	 */
	public static boolean isEven(int n) {
		return n % 2 == 0; //a number is even if it is evenly divisble by 2.
	}

	/**
	 * Prints out the even numbers across a given range of numbers.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final int numberElements = 100; //the range of numbers: from 1 to n.
		int[] numberRange = new int[numberElements]; //stores the numbers in the range.

		// initialize the range of numbers.
		for (int i = 0; i < numberRange.length; i++) {
			numberRange[i] = i + 1; //store the number.
		}
		
		// print out the even numbers
		for (int n : numberRange) { //run through each of the numbers in the range
			if (Question12.isEven(n)) { //if the number is even print it out
				System.out.println(n); //print out the even number
			}
		}
	}

}
