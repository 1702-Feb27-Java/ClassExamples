package Q9;

import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		//This loop puts the numbers 1-100 in the arraylist
		for(int i = 1; i <= 100; i++)
		{
			array.add(i);
			
			//If prime, print the number
			if(prime(i))
			{
				System.out.println(i);
			}
		}
		//System.out.println(array);
	}
	
	//This method figures out if the value is prime
	private static boolean prime(int value)
	{
		if(value == 1)
		{
			return false;
		}
		
		if(value == 2)
		{
			return true;
		}
		
		if(value % 2 == 0)
		{
			return false;
		}
		
		for(int j = 3; j * j <= value; j += 2)
		{
			if(value % j == 0)
			{
				return false;
			}
		}
		return true;
	}
}
