package com.revature.q10;

/**
 * @author Marco Tobon
 *
 * Find the minimum of two numbers using a ternary operator
 */
public class Question10
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int max= 500;
		int min = 5;
		System.out.println(getMin(max, min));
	}
	/**
	 * Find the minimum of two numbers using a ternary operator
	 * @param a int
	 * @param b int
	 * @return smaller of the ints
	 */
	public static int getMin(int a, int b)
	{
		return (a < b) ? a:b;
	}
}
