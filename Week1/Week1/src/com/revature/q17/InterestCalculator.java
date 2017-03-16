package com.revature.q17;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterestCalculator {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// declare doubles principal, interest rate, and years to be filled with user input
		double principal, interestRate, years;
		// try catch block, scans for user input
		try(Scanner input = new Scanner(System.in)){
			// user inputs principal
			System.out.println("Enter principal: ");
			principal = input.nextDouble();
			// user inputs interest rate
			System.out.println("Enter interest rate: ");
			interestRate = input.nextDouble();
			// user inputs years matriculated
			System.out.println("Enter number of years matriculated: ");
			years = input.nextDouble();
			// store calculated interest in interest var
			double interest = principal*interestRate*years;
			System.out.println("Total interest: " + interest);
		// display error message
		} catch(InputMismatchException e) {
			System.out.println("Please enter valid numbers ");
		}
	}

}
