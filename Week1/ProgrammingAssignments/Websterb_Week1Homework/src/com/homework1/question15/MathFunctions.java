package com.homework1.question15;

public class MathFunctions implements MathInterface{

	/**
	 * @param a the first number
	 * @param b the second number
	 * @return the addition of the 2 numbers
	 */
	public int addition(int a, int b){
		return a + b;
	};
	
	/**
	 * @param a the first number
	 * @param b the second number
	 * @return the subtraction of the 2 numbers
	 */
	public int subtraction(int a, int b){
		return a - b;
	};
	
	/**
	 * @param a the first number
	 * @param b the second number
	 * @return the multiplication of the 2 numbers
	 */
	public int multiplication(int a, int b){
		return a*b;
	};
	
	/**
	 * @param a the first number
	 * @param b the second number
	 * @return the division of the 2 numbers
	 */
	public int division(int a, int b){
		return a/b;
	};
	
}
