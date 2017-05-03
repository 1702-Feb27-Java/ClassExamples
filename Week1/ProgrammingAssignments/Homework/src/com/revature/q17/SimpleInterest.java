package com.revature.q17;
import java.util.Scanner;

/**
 * This program will demonstrate the simple interest with the user inputing the 
 * Principal, rate, and time(years)
 * @author Nick
 *
 */
public class SimpleInterest {

	public static void main(String[] args) {
		Interest();

	}
	
	static void Interest() {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter the your principal: ");
		double prin = keyboard.nextDouble();
		
		System.out.print("Enter your rate: ");
		double rate = keyboard.nextDouble();
		
		System.out.print("Enter you time(in years): ");
		int time = keyboard.nextInt();
		
		keyboard.close();
		System.out.println( InterestCompute(prin, rate, time));
	}
	
	/**
	 * Function to compute the simple interest
	 * @param p principal of type double
	 * @param r rate of type double
	 * @param y time of int type (in years)
	 * @return result of type double
	 */
	static double InterestCompute(double p, double r, int y) {
		return p * (1 + (r / 100) * y);
		
		
	}
	
	
}
