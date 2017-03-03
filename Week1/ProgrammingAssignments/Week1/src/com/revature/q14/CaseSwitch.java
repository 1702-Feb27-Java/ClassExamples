package com.revature.q14;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Scanner;
/**
 * 
 * @author Aaron Camm
 *
 */
public class CaseSwitch {
	
	/**
	 * Prompts the User to display a square root of a number, show today's date, or print out a string
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		//Keep going back to the menu below everytime,
		//until the user puts something besides 1, 2, and 3.
		while(true){
			System.out.println("1: Print out the square root of a number.");
			System.out.println("2: Display today's date");
			System.out.println("3: Split the string, \"I am learning Core Java \" and store it");
			System.out.println("Any other to exit.");
			System.out.print("Please input your option: ");
			Scanner scan = new Scanner(System.in);
			


			//reads user input from stdin
			int result = Integer.parseInt(scan.nextLine());
			//clears out anything left in buffer
			System.out.println();
			
			
			switch (result) {
			case 1:
				//ask user a number to display its sqrt
				System.out.print("Please enter a number to get a square root: ");
				double n = Double.parseDouble(scan.nextLine());
				
				System.out.println();
				System.out.println(Math.sqrt(n));
				break;
			case 2:
				
				//Prints out today's date from LocalTime in "WEEKDAY MONTH DAY, YEAR" format
				SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");
				System.out.println("Today's Date is " + format.format(new Date()));
				break;
			case 3:
				String string = "I am learning Core Java";
				// Splits a string by a space and puts each word into string array
				String[] stringArray = string.split(" ");
				
				//prints out each item in the array
				for (String s: stringArray){
					System.out.println(s);
				}
				break;
			default:
				
				//closes Scanner to prevent resource leak.
				scan.close();
				System.exit(0);
			
			
			}
			
			// new line for cleanliness of text
			System.out.println();
			}
			
		
		
	}
}
