package com.revature.weekone.question2;

/**
 * Displays an arbitrary number of numbers from the Fibonacci sequence.
 * 
 * When executed it displays the first 25 such numbers beginning at 0.
 * 
 * @author Michael Hobbs
 *
 */
public class Question2 {
	
	/**
	 * Prints numbers from the Fibonacci sequence up to and including the nth number.
	 * 
	 * @param n the number of numbers from the Fibonacci sequence to print
	 */
	public static void displayFibonacci(int n) {
		//check zero and negative cases
		if (n <= 0) {
			System.out.println("Invalid number");
			return;
		}
		
		//handle printing out first number in the sequence
		if (n == 1) {
			System.out.println(0); //the first number
		}
		//handle printing out the first 2 numbers in the sequence
		else if (n == 2) {
			System.out.println(0); //the first number
			System.out.println(1); //the second number
		}
		//handle printing out n numbers in the sequence
		else {
			System.out.println(0); //the first number
			System.out.println(1); //the second number
			
			int first = 0, second = 1; //store the current position in the sequence by remembering the previous 2 numbers from the current position
			int third = 0; //store the current number in the sequence at the current position
			
			for (int i = 0; i <= n; i++) { //print up to the nth number in the sequence by iterating through the sequence
				third = first + second; //after the first 2 digits in the Fibonacci sequence, a number is the sum of the previous 2 numbers
				first = second; //move up through the sequence
				second = third; //"
				System.out.println(third); //print out the current number in the sequence
			}
		}
		
		
	}
	
	/**
	 * Displays the Fibonacci sequence up to 25 digits.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Question2.displayFibonacci(25); //displays the Fibonacci sequence up to the 25th number in the sequence
		
	}

}
