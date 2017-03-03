package com.revature.Question17;

import java.util.Scanner;

public class Interest {

	public static void main(String[] args) {
		
		//Create Scanner Input
		Scanner keyboard = new Scanner(System.in);
		
		//User prompt to get the principal for simple interest
		System.out.println("Enter in the principal: ");
		double principal = keyboard.nextDouble();
		
		//User prompt to get the rate at which the simple interest is calculated
		System.out.println("Enter in the rate: ");
		double rate = keyboard.nextDouble();
		
		//User prompt for how many years in accrues interest at the rate enter above
		System.out.println("Enter in how many years: ");
		double time = keyboard.nextDouble();
		
		//Closes input stream
		keyboard.close();
		
		//Formats the principal and total to 2 decimal places, commas for money values
		String princ = String.format("%,.2f",principal);
		String total = String.format("%,.2f",(principal*rate*time));
		
		//Prints out all values entered and provides the Simple Interest accrued for specific time period
		System.out.println("The Simple Interest for a Principal of $"
		+ princ + "\na rate at " + rate + "%\n, and for " 
				+ time + " years is: $" + total);
	}

}
