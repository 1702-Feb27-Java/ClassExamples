package com.mory.question10;

public class Q10 {

	public static void main(String[] args) {
		System.out.println(findMinimum(-2, 5));

	}
	/***
	 * 
	 * @param a fist number
	 * @param b second number
	 * @return the smallest of the two parameters
	 */
	public static int findMinimum(int a, int b) {
		return (a < b) ? a : b;

	}

}
