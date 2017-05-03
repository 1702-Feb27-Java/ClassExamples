package com.revature.q17;

import java.util.Scanner;

/**
 * 
 * @author tobon
 * calculates simple interest rate
 */
public class Question17
{
	/**
	 * 
	 * @param principal
	 * @param rate
	 * @param time
	 * @return double  
	 * 
	 * Calculates simple interest as
	 * principal * (rate/100)*time
	 */
	public static double simpleInterest (int principal, double rate, int time)
	{
		return (principal*(rate/100)*time);
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Principal: $");
		int princ = sc.nextInt();
		System.out.print("Enter Rate: ");
		double rate = sc.nextDouble();
		System.out.print("Enter Number of Years: ");
		int years = sc.nextInt();
		sc.close();
		double interest = simpleInterest(princ, rate, years);
		System.out.println("\nYour simple interest is: $" + interest);
	}

}
