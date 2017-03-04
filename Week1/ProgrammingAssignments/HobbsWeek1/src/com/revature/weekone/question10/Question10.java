package com.revature.weekone.question10;

/**
 * Finds the minimum of two numbers.
 * 
 * It uses the ternary operator in doing so.
 * 
 * @author Michael Hobbs
 *
 */
public class Question10 {
	
	/**
	 * Finds the minimum of two numbers.
	 * 
	 * @param a the first number
	 * @param b the second number
	 * @return the minimum of the two parameters
	 */
	public static int min(int a, int b) {
		return a <= b ? a : b; //checks which of the two numbers is the smaller and returns that one.
	}

	/**
	 * Checks some numbers to see which of the two is the smaller.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// initialize some numbers to check the minimum
		int a = 10, b = 5, c = 15, d = 0, e = -1, f = 10;
		
		// check which is minimum of some numbers and print which is the smaller one.
		System.out.println("min(" + a + ", " + b + "): " + Question10.min(a, b));
		System.out.println("min(" + a + ", " + c + "): " + Question10.min(a, c));
		System.out.println("min(" + a + ", " + d + "): " + Question10.min(a, d));
		System.out.println("min(" + a + ", " + e + "): " + Question10.min(a, e));
		System.out.println("min(" + a + ", " + f + "): " + Question10.min(a, e));
		System.out.println("min(" + a + ", " + a + "): " + Question10.min(a, a));
		
		// check which is minimum of some numbers and print which is the smaller one.
		System.out.println("min(" + b + ", " + a + "): " + Question10.min(b, a));
		System.out.println("min(" + c + ", " + a + "): " + Question10.min(c, a));
		System.out.println("min(" + d + ", " + a + "): " + Question10.min(d, a));
		System.out.println("min(" + e + ", " + a + "): " + Question10.min(e, a));
		System.out.println("min(" + f + ", " + a + "): " + Question10.min(f, a));
		System.out.println("min(" + a + ", " + a + "): " + Question10.min(a, a));
		
	}

}
