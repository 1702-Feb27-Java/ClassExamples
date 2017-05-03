package com.revature.q9;

import java.util.ArrayList;

/**
 * 
 * @author tobon
 * Creates and arraylist from 1-100 and prints out prime numbers
 */
public class Question9
{
	public static void main (String[] args)
	{
		//FILL ARRAYLIST FROM 1-100
		ArrayList<Integer> indexOfPrime = new ArrayList<Integer>();
		ArrayList<Integer> numbers= new ArrayList<Integer>();
		for (int i =0; i <100; i++)
		{
			numbers.add(i+1);
			boolean returnValue = isPrime(i+1);
			if (returnValue)
			{
				Integer num = new Integer (i+1);
				indexOfPrime.add(num);
			}
		}
		for (int prime : indexOfPrime)
		{
			System.out.println(prime);
		}
	}
/**
 * 	
 * @param check
 * @return boolean
 * 
 * prints true if check is prime
 * else false if its not prime
 */
	public static Boolean isPrime(int check)
	{
		if (check == 2) 
		{
			return true;
		}
		else if (check % 2 != 0)
		{
			for ( int i = 3; i < check/2; i++)
			{
				if (check%i == 0)
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
