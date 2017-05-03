package com.revature.q19;

import java.util.ArrayList;

/**
 * 
 * @author tobon
 *
 * Puts up 1-10 in an arraylist
 * 1.Adds evens
 * 2.Adds odds
 * 3.Removes prime numbers from list and prints out what is left 
 */
public class Question19
{
	public static void main(String[] args)
	{
		ArrayList<Integer> intLit = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
		{
			intLit.add(i+1);
			System.out.print((i+1) + " ");
		}
		System.out.println();
		
		System.out.print("The addition of all the even numbers: "+ addUp(intLit, "even") +"\n");
		System.out.print("The addition of all the odd numbers: "+ addUp(intLit, "odd") +"\n");
		
		System.out.println("Printing list with all primes removed: ");
		
		ArrayList<Integer> primes = new ArrayList<>();
		for(Integer x : intLit)
		{
			if (isPrime(x))
			{
				primes.add(x);
			}
		}
		for (Integer x: primes)
		{
			intLit.remove(x);
		}
		for (Integer x : intLit)
		{
			System.out.print(x + " ");
		}
		
	}
	
	/**
	 * 
	 * @param list
	 * @param type
	 * @return int the addition of even/odd from the array list
	 * list = the list that contains the integers
	 * type = if you want to add the even or odd
	 */
	//ADD UP THE NUMBERS IN THE LIST (ODD OR EVEN)
	public static int addUp(ArrayList<Integer> list, String type)
	{
		int start = 0;
		int total = 0;
		if (type.equals("even"))
		{
			start = 1;
		}
		for (int i =start; i < list.size(); i+=2)
		{
			total += list.get(i);
		}
		return total;
	}
	
	/**
	 * 
	 * @param check
	 * @return boolean
	 * 
	 * checks if the given int is prime or evenly divs
	 * returns true if prime
	 * false if evenly divisible
	 */
	//REMOVE ALL THE PRIMES
	public static boolean isPrime(int check)
	{
		if(check == 2)
			return true;
		else if (check % 2 != 0 )
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
