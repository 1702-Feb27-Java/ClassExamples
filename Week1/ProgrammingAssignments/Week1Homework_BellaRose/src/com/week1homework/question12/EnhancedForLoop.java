package com.week1homework.question12;

public class EnhancedForLoop
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		int[] arr = new int[100];
		
		for (int i = 0; i < 100; i++)
		{
			arr[i] = i + 1;
		}
		
		for (int i : arr)
		{
			if (i % 2 == 0)
				System.out.println(i + " is an even number.");
		}

	}

}
