package com.revature.Question03;

//Imports the Scanner class capability to allow user input
import java.util.Scanner;

public class StringReversal {
	public static void main(String[] args){
		
		//Creates a keyboard instance to allow a user to enter in a value
		Scanner keyboard = new Scanner(System.in);
		
		//Prompts the user to enter in a string to reverse
		System.out.println("Enter in a string to reverse: ");
		
		//Gets the users input string
		String myString = keyboard.nextLine();
		
		//Closes input stream
		keyboard.close();
				
		//Loop to reverse the string starting at the second to last character
		//and works its way to the front
		for (int i = myString.length()-2; i > -1;i--){
			myString = myString + myString.charAt(i);
			myString = myString.substring(0,i)+myString.substring(i+1);
		}
		
		//Prints out the reverse of the string inputted
		System.out.println(myString);
		
	}
}
