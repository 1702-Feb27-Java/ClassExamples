package com.revature.q15;

/**
 * 
 * @author tobon
 * Class that implements our CalcFunctions
 * addition
 * subtraction
 * multiplication
 * division
 */
public class Calculator implements CalcFunctions
{
	/**
	 * @param a first int to add
	 * @param b second int to add
	 * @return int addition of first 2 args
	 */
	public int addition(int a, int b)
	{
		return a + b;
	}
	/**
	 * @param a first int 
	 * @param b second int to subtract
	 * @return int subtraction of first 2 args
	 *
	 */
	public int subtraction(int a, int b)
	{
		return a-b;
	}
	/**
	 * @param a first int 
	 * @param b second int to multiply
	 * @return int multiplication of first 2 args
	 *
	 */
	public int multiplication(int a, int b)
	{
		return a*b;
	}
	/**
	 * @param a first int 
	 * @param b second int to divide
	 * @return int divide of first 2 args
	 *
	 */
	public int division(int a, int b)
	{
		return a/b;
	}
}
