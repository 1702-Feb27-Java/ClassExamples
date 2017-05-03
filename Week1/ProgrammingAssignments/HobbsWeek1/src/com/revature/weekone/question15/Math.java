package com.revature.weekone.question15;

/**
 * Performs basic arithmetic operations.
 * 
 * @author Michael Hobbs
 *
 */
public class Math implements IMath {
	
	public int add(int a, int b) {
		return a + b; //add the numbers
	}
	
	public int subtract(int a, int b) {
		return a - b; //subtract the numbers
	}
	
	public int multiply(int a, int b) {
		return a * b; //multiply the numbers
	}
	
	public double divide(double a, double b) {
		return a / b; //divide the numbers
	}

}
