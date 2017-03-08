package com.revature.Question04;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		
		//Creates a keyboard object to allow the user to enter in a value
		Scanner keyboard = new Scanner(System.in);
		
		//Declares number as an integer
		int number;
		
		//Loop that makes sure a user does not enter in a number less than 1
		//and repeats until they enter in a correct entry
		do{
			//Prompts user for numerical input
			System.out.println("Enter in a number to determine its factorial: ");
			
			//Gets the value the user entered and stores it in number variable
			number = keyboard.nextInt();
		}while (number <=0); //Conditional Check
		
		//Closes input stream
		keyboard.close();
		
		
		//Starting value for total variable to multiply the first number in sequence
		int total = 1;
		
		//Loop to start at the number and continue to multiply and add to total until 1
		for (int i = number; i > 0;i--){
			total*= i;
		}
		
		//Prints of the factorial of the number entered by the user
		System.out.println(total);
	}
}
