package com.revature.q02;

import java.util.Arrays;

public class Fibonacci {
	/**
	 * Display first 25 fib numbers beginning at 0
	 * @param args
	 */
	public static void main(String[] args) {
		// input
		int input = 25;
		// create Fibonacci instance
		Fibonacci f = new Fibonacci();
		// init returnArr to fib result
		int[] returnArr = f.fib(input);
		// display result
		System.out.println(Arrays.toString(returnArr));
	}
	/**
	 * 
	 * @param count	number of fibonacci numbers to list
	 * @return fibs resultant fibonacci array
	 */
	public int[] fib(int count) {
		// init fib array with enough space to hold 'count' number of fib numbers
		int[] fibs = new int[count];
		// init first two fib constants
		fibs[0] = 0;
		fibs[1] = 1;
		// iterate fibonacci sequence count-2 times (discounting first two constants)
		for(int i = 2; i < count; i++){
			fibs[i] = fibs[i-2] + fibs[i-1];
		}
		return fibs;
	}
}
