package com.revature.q10;

public class TernaryOperator {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// initialize test values
		int a, b;
		a = 6;
		b = 3;
		// display result
		System.out.println(findMin(a,b));
	}
	
	/**
	 * 
	 * @param a	integer input 1
	 * @param b integer input 2
	 * @return minimum value
	 */
	static int findMin(int a, int b){
		// find minimum value using ternary operator
		return (a < b) ? a : b;
	}

}
