package com.revature.weekone.question15;

/**
 * Performs basic arithmetic operations.
 * 
 * @author Michael Hobbs
 *
 */
public interface IMath {

	/**
	 * Adds two numbers.
	 * 
	 * @param a the first number
	 * @param b the second number
	 * @return the sum of a and b
	 */
	public int add(int a, int b);
	
	/**
	 * Subtracts two numbers.
	 * 
	 * @param a the first number
	 * @param b the second number
	 * @return the difference of a and b
	 */
	public int subtract(int a, int b);
	
	/**
	 * Multiplies two numbers.
	 * 
	 * @param a the first number
	 * @param b the second number
	 * @return the product and a and b
	 */
	public int multiply(int a, int b);
	
	/**
	 * Divides two numbers.
	 * 
	 * @param a the first number
	 * @param b the second number
	 * @return the quotient of a and b
	 */
	public double divide(double a, double b);
	
}
