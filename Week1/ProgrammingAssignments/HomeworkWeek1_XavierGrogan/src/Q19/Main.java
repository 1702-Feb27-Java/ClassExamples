package Q19;

import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList array = new ArrayList();
		for(int i = 0; i < 10; i++)
		{
			array.add(i + 1);
		}
		System.out.println("Original Array: " + array);
		
		//Summing up the even numbers in the ArrayList
		int evenResult = 0;
		for(int j = 0; j < array.size(); j++)
		{
			int evenTemp;
			evenTemp = (int)array.get(j);
			if(evenTemp % 2 == 0)
			{
				evenResult += evenTemp;
			}
		}
		System.out.println("Sum of the even numbers: " + evenResult);
		
		//Summing up the odd numbers in the ArrayList
		int oddResult = 0;
		for(int k = 0; k < array.size(); k++)
		{
			int oddTemp;
			oddTemp = (int)array.get(k);
			if(oddTemp % 2 != 0)
			{
				oddResult += oddTemp;
			}
		}
		System.out.println("Sum of the odd numbers: " + oddResult);
		
		//Removing the prime numbers and re-displaying the ArrayList
		int[] primes = {2,3,5,7};
		for(int i = 0; i < array.size(); i++)
		{
			for(int j = 0; j < primes.length; j++)
			{
				if((int)array.get(i) == primes[j])
				{
					array.remove(i);
					i--;
				}
			}
		}
		System.out.println("ArrayList without the primes: " + array);
	}
}
