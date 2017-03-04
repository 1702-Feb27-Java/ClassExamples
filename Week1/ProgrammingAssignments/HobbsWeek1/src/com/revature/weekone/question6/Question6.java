package com.revature.weekone.question6;

/**
 * Determines whether a number is even.
 * 
 * It does not use the modulus operator ( % ) to determine this.
 * 
 * @author Michael Hobbs
 *
 */
public class Question6 {
	
	/**
	 * Checks whether a number is even.
	 * 
	 * @param n the number
	 * @return true if the number is even
	 */
	public static boolean isEven(int n) {
		//integer division of odd results in truncation
		//so doubling such a result would not be equal to the original number!
		//when it is not equal, then the number is odd.
		if (n / 2 * 2 == n) { //an even number is equal to half itself (computed via integer division) times two.
			return true; //the number is even
		}
		else {
			return false; //the number is odd
		}
	}
	
	/**
	 * Checks whether a certain number is even and prints out the result of the check.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		int n = 11; //the number to be checked whether it is even.
		
		if (Question6.isEven(n)) { //check whether the number is even
			System.out.println("The number is even."); //indicate if it is even
		}
		else { //if the number is not even
			System.out.println("The number is not even."); //indicate if it is not even
		}
		
	}

}
