package com.revature.q2;

/**
 * 
 * @author tobon
 * 
 * Prints the first 25 Fibonacci numbers
 *
 */
public class Question2 {

	public static void main(String[] args) {
		
		//BASE LINE 0
		int prev =0;
		System.out.print(prev+",");
		
		//BASE LINE 1
		int fib=1;
		System.out.print(fib+",");
		
		//NUMBER WE WILL ADD TO BE CURRENT FIB SEQUENCE
		int curr = 0;
		
		//23 NUMBERS LEFT SINCE THE FIRST 2 ARE BASES
		for (int i = 0; i < 24; i++)
		{
			curr= prev;
			System.out.print(fib+prev+",");
			prev = fib;
			fib = curr + fib;
		}
	}

}
