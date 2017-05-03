package com.revature.q4;

/**
 * 
 * @author tobon
 * Computes the N factorial of a given number
 */
public class Question4 {
	
	/**
	 * 
	 * @param start
	 * @return int 
	 * Returns the factorial of the start param
	 */
	//RECURSIVE FUNCTION
	public static int factorial (int start)
	{
		//STOP WHEN WE REACH 1
		if (start == 1)
		{
			return start;
		}
		
		//ELSE RETURN N*N-1
		return start * (factorial(start-1));
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println( factorial(5));
	}

}
