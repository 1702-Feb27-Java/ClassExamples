package com.revature.Question06;

import java.util.Scanner;

public class EvenOrOdd {

	public static void main(String[] args) {
				
		//Creating an input object
		Scanner keyboard = new Scanner(System.in);
		
		//Declaring num1 variable for user entry
		int num1;
		
		//Conditional loop for the user to enter in a positive number
		do{
			//Prompts user for a positive number
			System.out.println("Enter in a positive number to check to see if it is Even or Odd: ");
			
			//Gets the users input and assigns num1 the value entered
			num1 = keyboard.nextInt();
						
		}while(num1 < 0);
		
		//Closes input stream
		keyboard.close();

		//Calls the method to determine even or odd of the number entered in by user
		determineEvenOdd(num1);
	}
	
	//Method to determine if a number is even or odd
	public static void determineEvenOdd(int num1){ //number passed into method, e.g. 11
		int num2 = num1/2; //divides number by 2, if it is an odd number it will round down, e.g. 11/2 = 5
		int num3 = num2 * 2; //multiplication of the number obtained in division statement by 2, e.g. 2 * 5 = 10
		
		if (num1 == num3){ //When compared num1 = 11 and num3 = 10, so they are unequal
			System.out.println("The number is even"); 
		}
		else
			System.out.println("The number is odd"); 
	}
}
