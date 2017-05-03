package com.revature.Question10;

import java.util.Scanner;

public class Ternary {

	public static void main(String[] args) {
		
		//Creating a input object keyboard
		Scanner keyboard = new Scanner(System.in);
		
		//Prompt user for first number to find the minimum
		System.out.println("Enter in the first number to compare: ");
		int number1 = keyboard.nextInt();

		//Closes input stream
		keyboard.close();
				
		//Prompt user for second number to find the minimum
		System.out.println("Enter in the second number to compare: ");
		int number2 = keyboard.nextInt();
		
		//Comparing the two numbers, finding the minimum of the two, and assigning to variable min
		int min = (number1 < number2) ? number1 : number2;
		System.out.println(min);  //Displaying the min variable
	}
}
