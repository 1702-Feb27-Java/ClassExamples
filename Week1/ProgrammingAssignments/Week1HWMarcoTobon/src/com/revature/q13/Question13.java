package com.revature.q13;
/**
 * 
 * @author tobon
 *
 * Prints out a triangle
 * 0
 * 1 0
 * 1 0 1
 * 0 1 0 1
 */

public class Question13
{
	public static void main(String[] args)
	{
		boolean isZero = true;
		// TODO Auto-generated method stub
		for ( int i = 0; i < 4; i++)
		{
			for ( int j = 0; j < i+1; j++)
			{
				if (isZero)
				{
					System.out.print(0 + " ");
					isZero= false;
				}
				else
				{
					System.out.print(1 + " ");
					isZero= true;
				}
			}
			System.out.println();
		}
	}
}
