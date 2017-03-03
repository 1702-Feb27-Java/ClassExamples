package com.revature.q1;
/**
 * Performs bubble sort on an array
 * 
 * 
 * @author Marco Tobon
 */
public class Question1 {
	/**
	 * Method performs sort on the int array parameter
	 * @param arr [] the array to be sorted
	 * @return int [] a sorted array
	 */
	public static int[] Bubblesort(int[] arr)
	{
		boolean swapped = false;
		int size = arr.length;
		//Do check until everything is sorted
		do 
		{
			//Variable to see if we swap at any moment in array pass 
			//It might still not be sorted
			swapped = false;
			for (int i =1; i < size; i++)
			{
				if (arr[i] < arr[i-1])
				{
					int temp = arr[i];
					arr[i]=arr[i-1];
					arr[i-1] = temp;
					swapped = true;
				}
			}
		}
		while (swapped);
		
		//Return sorted array
		return arr;
	}
	public static void main(String[] args) {
		//Array to sort
		int[] ToSort = {1,0,5,6,3,2,3,7,9,8,4};
		ToSort= Bubblesort(ToSort);
		
		for (int x: ToSort)
		{
			System.out.println(x);
		}
		
	}
}
