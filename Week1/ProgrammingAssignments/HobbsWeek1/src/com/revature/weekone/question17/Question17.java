package com.revature.weekone.question17;

import java.util.Scanner;

/**
 * Calculates the interest for a given principal, rate, and time period.
 * 
 * @author Michael Hobbs
 *
 */
public class Question17 {
	
	/**
	 * Calculates the interest for a given principal, rate, and time period.
	 * 
	 * Interest is calculated as the product (principal * rate * time).
	 * 
	 * @param principal
	 * @param rate
	 * @param time
	 * @return the interest calculated
	 */
	public static double calculateInterest(double principal, double rate, double time) {
		return principal * rate * time;
	}

	/**
	 * Calculates the interest on the principal, rate, and time period input by a user.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); //get input from keyboard
		boolean isValidInput = false; //flag for when valid input has been input
		
		double principal = 0; //hold the principal input by the user
		double rate = 0; //hold the rate input by the user
		double time = 0; //hold the time input by the user
		
		// get input for the principal
		isValidInput = false;
		while (!isValidInput) { //don't move on until the user has input a valid principal
			System.out.print("Enter the principal: ");
			String principalInput = input.nextLine();
			try {
				principal = Double.parseDouble(principalInput);
				isValidInput = true;
			}
			catch (NumberFormatException e) {
				System.out.println("Error: Principal must be a number");
			}
		}
		
		// get input for the rate
		isValidInput = false;
		while (!isValidInput) { //don't move on until the user has input a valid rate
			System.out.print("Enter the rate: ");
			String rateInput = input.nextLine();
			try {
				rate = Double.parseDouble(rateInput);
				isValidInput = true;
			}
			catch (NumberFormatException e) {
				System.out.println("Error: Rate must be a number");
			}
		}
		
		// get input for the time
		isValidInput = false;
		while (!isValidInput) { //don't move on until the user has input a valid time
			System.out.print("Enter the time: ");
			String timeInput = input.nextLine();
			try {
				time = Double.parseDouble(timeInput);
				isValidInput = true;
			}
			catch (NumberFormatException e) {
				System.out.println("Error: Time must be a number");
			}
		}
		
		input.close(); //close the input once all input has been retrieved from the user
		
		// calculate and display the interest with the given principal, rate, and time
		double interest = Question17.calculateInterest(principal, rate, time); //calculate the interest
		System.out.println("Principal: " + principal);
		System.out.println("Rate: " + rate);
		System.out.println("Time: " + time);
		System.out.println("Calculated interest: " + interest);
		
	}

}
