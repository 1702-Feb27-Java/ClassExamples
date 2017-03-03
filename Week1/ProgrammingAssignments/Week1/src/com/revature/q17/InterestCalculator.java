package com.revature.q17;

import java.util.Scanner;
/**
 * 
 * @author Aaron Camm
 *
 */
public class InterestCalculator {

	/**
	 * Prompts the User to input values for principal, rate, and years. <BR>
	 * Outputs the interest from the values given.
	 * @param args
	 */
	public static void main(String[] args){
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to the Simple Interest Calculator.");
		System.out.print("Please input your Principal: ");
		//goes with doubles because Money is often handled with Decimals 
		
		double principal = Double.parseDouble(scan.nextLine());
		
		System.out.print("Please input your rate: ");
		
		double rate = Double.parseDouble(scan.nextLine());
		
		System.out.print("Please input how many years: ");
		
		double years = Double.parseDouble(scan.nextLine());
		
		double interest = principal * rate * years;
		
		System.out.println("The interest is " + interest);
		
		
		
		scan.close();
	}
	
}
