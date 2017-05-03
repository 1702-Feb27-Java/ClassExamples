package com.revature.Question14;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SwitchQuestion {

	public static void main(String[] args) {
		
		//Creates input object
		Scanner keyboard = new Scanner(System.in);
		
		//Calls the method to display the menu
		displayMenu();
		
		//User prompt for the menu item number to be passed to switch method
		System.out.print("\nEnter in a number from the menu above: ");
		
		//Gets users selection
		int choice = keyboard.nextInt();

		//Closes input stream
		keyboard.close();
		
		//Calls the selection method
		selection(choice);
	}
	
	//Menu Method
	public static void displayMenu(){
		System.out.println("Menu Choices");
		System.out.println("1: Find Square Root of a number");
		System.out.println("2: Display Today's Date");
		System.out.println("3: Split String");
	}
	
	//Switch method to determine which capability the user selected
	public static void selection(int i){
		switch(i){
		case 1:
			sqRoot(); //method call for square root
			break;
		case 2:
			displayDate(); //method call for today's date
			break;
		case 3:
			splitString(); //method call to split the given string
			break;
		default: //prints if the user enters in an invalid option
			System.out.println("You did not enter in a valid option!!!");
			break;
		}
	}
	
	//Square Root Method
	public static void sqRoot(){
		Scanner keyboard = new Scanner(System.in); //Create input object
		System.out.print("Enter in a number to find the square root of: "); //User prompt
		double sqr = keyboard.nextDouble(); //getting user input
		keyboard.close();
		double sqroot = Math.sqrt(sqr); //square rooting the number entered
		System.out.println(sqroot); //displaying the square root
	}
	
	//Displaying the Current Date Method
	public static void displayDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy"); //formatter for the date
		LocalDateTime now = LocalDateTime.now(); //getting the local time from computer
		System.out.println(dtf.format(now)); //displaying the current date
	}
	
	//Putting the given string into an array and printing it out
	public static void splitString(){
		
		String s = "I am leaving Core Java"; //given string to split
		String[] result = s.split(" "); //splitting string by space character
		for (String token:result){
			System.out.println(token); //printing out each individual word in the array of strings
		}
	}
}
