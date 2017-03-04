package com.week1homework.question4;
import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Scanner object used to retrieve user input
		Scanner scan = new Scanner(System.in);
		
		//Print out simple info to understand console output
		System.out.println("Insert a  whole number to get Factorial: ");
		
		//User has input a number and we shall set it to our variable called value
		long value = scan.nextLong();
		
		//Use cases for value being 0 and 1. 0! is 0 and 1! is 1
		if (value == 0)
		{
			System.out.println("Factorial of " + value + " is " + 0);
		}
		else if (value == 1)
		{
			System.out.println("Factorial of " + value + " is " + 1);
		}
		else
		{
			System.out.println("Factorial of " + value + " is " + Factorial(value));
		}
		
	}
	
	//Recursive function
	private static long Factorial(long num)
	{
		//End of our recursive loop, start with 1 for the next step
		if(num == 0)
			return 1;
		else
		{
			//Starting with the 1 above go all the way up the list, above 1 is 2 so 2*1 = 2, after 2 is 3, 2*3 = 6 and so on till you have multiplied value and the number returned
			return num * Factorial(num-1);
		}
			
	}

}
