package com.revature.samsel_q17;

import java.util.Scanner;

/* Question : Write a program that calculates the simple Interest. Enter the Principal,
 * 			  rate and time through the console using Scanner class
 */

/* Input  : Principal (double), Rate (double), Time in years (int)  
 * Output : Simple Interest (double) 
 */
public class MyScannerCalc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//User Input : Principal
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Principal : ");
		String sPrincipal = sc.nextLine();
		
		//User Input : Rate of Interest
		System.out.println("Enter the Rate : ");
		String sRate = sc.nextLine();
		
		//User Input : Time
		System.out.println("Enter the Time (YEARS) : ");
		String sTime = sc.nextLine();
		
		//Parse the input into its respective types
		double dP  = Double.parseDouble(sPrincipal);
		double dR  = Double.parseDouble(sRate);
		int    iT  = Integer.parseInt(sTime); 
	
		/* Display the Simple Interest Calculated 
		 * [Principal * Rate * Time]
		 */
		System.out.println("Simple Interest : "+(dP*dR*iT));
	}
}
