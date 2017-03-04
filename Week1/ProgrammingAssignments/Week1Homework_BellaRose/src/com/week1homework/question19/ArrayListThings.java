package com.week1homework.question19;

import java.util.ArrayList;

public class ArrayListThings
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		ArrayList arr = new ArrayList();
		
		for(int i = 0; i < 10; i++)
		{
			arr.add(i + 1);
		}
		System.out.println("Original Array: " + arr);
		
		
		int even = 0;
		
		for(int j = 0; j < arr.size(); j++)
		{
			int evenTemp;
			evenTemp = (int)arr.get(j);
			if(evenTemp % 2 == 0)
			{
				even += evenTemp;
			}
		}
		System.out.println("Sum of the even numbers: " + even);
		
		
		int odd = 0;
		for(int k = 0; k < arr.size(); k++)
		{
			int oddTemp;
			oddTemp = (int)arr.get(k);
			if(oddTemp % 2 != 0)
			{
				odd += oddTemp;
			}
		}
		System.out.println("Sum of the odd numbers: " + odd);
		
		
		int[] primeNums = {2,3,5,7};
		for(int i = 0; i < arr.size(); i++)
		{
			for(int j = 0; j < primeNums.length; j++)
			{
				if((int)arr.get(i) == primeNums[j])
				{
					arr.remove(i);
					i--;
				}
			}
		}
		System.out.println("ArrayList without the primes: " + arr);

	}

}
