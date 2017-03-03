package com.revature.Question05;

import java.util.Scanner;

public class Substring {
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in); //Creates an input object
		System.out.print("Enter in a string: "); //User prompt for a string
		String str = keyboard.nextLine(); //Getting the line from the user
		
		//Declaring integer idx
		int idx;
		
		//Loop to ensure the user does not enter in a number greater than the string length
		do{
			System.out.print("Enter in a number to get a substring from the beginning: "); //User prompt
			idx = keyboard.nextInt(); //Getting the number of chars the user wants to substring too
		}while (str.length() < idx);  //Conditional check to ensure the number entered does not go past the string length
		
		//Closes input stream
		keyboard.close();
		
		String s = substring(str, idx); //Calling the substring method and assigning it to s
		System.out.println(s); //Printing out the substring
	
	}
	
	//Substring Method based on user string and how many characters they want to get from the first char to the number
	public static String substring(String str, int idx){
		String s = new String(); //Creating a new string object
		
		//Loop to get each character from the beginning to the desired character
		for (int i = 0; i < idx-1; i++){
			s += str.charAt(i);
		}
		return s; //Returning the string
		
	}
}
