package com.revature.q12;

/***
 * 
 * @author marco tobon
 *
 * Print even numbers from 1-100 in an array list
 * using an enhanced for loop
 */
public class Question12
{

	public static void main(String[] args)
	{
		int [] numberList = new int [100];
		//FILL THE ARRAY FROM 1-100
		for(int i =0; i < numberList.length; i++)
		{
			numberList[i] = i+1;
		}
		//ENHANCED FOR LOOP
		for(int x: numberList)
		{
			//IF THE NUMBER IS EVEN PRINT IT OUT
			
			if (numberList[x]%2==0)
			{
				System.out.println(numberList[x]);
			}
		}
	}

}
