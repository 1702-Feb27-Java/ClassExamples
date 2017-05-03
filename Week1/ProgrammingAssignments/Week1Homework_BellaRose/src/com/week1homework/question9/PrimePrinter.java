package com.week1homework.question9;

import java.util.ArrayList;

public class PrimePrinter
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		ArrayList<Integer> numbers = new ArrayList<>();
		
		for (int i = 0; i < 100; i++)
		{
			numbers.add(i + 1);
			
			if (isPrime(i + 1))
				System.out.println(i + 1 + " is Prime");
		}

	}
	
	private static boolean isPrime(int value)
	{
		//1 is definitely prime
		if (value == 1 || value == 2)
			return true;
		
		//Is value Even???
	    if (value % 2 == 0)
	    	return false;
	    
	    //Go thru odds besides 1 since we know 1 is prime
	    for(int i = 3;i * i <= value;i += 2) 
	    {
	        if(value % i == 0)
	            return false;
	    }
	    return true;
		
	}

}
