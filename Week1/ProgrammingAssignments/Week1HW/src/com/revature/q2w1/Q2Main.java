// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 2 - WRITE A PROGRAM TO DISPLAY THE FIRST 25 FIBONACCI NUMBERS BEGINNING AT 0

package com.revature.q2w1;

public class Q2Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] fib;
		fib = new int[25]; // array size allocated for the fibonacci sequence array
		fib[0] = 0; // first # of the sequence
		fib[1] = 1; // second # of the sequence
		
		FindFib newFib = new FindFib(); // creates a new fib object
		newFib.fibcalc(fib); // run the fib calc method to find the first 25 numbers of the sequence
		
	}

}
