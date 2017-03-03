package com.revature.Q17;

import java.util.Scanner;

public class Q17 {

	public static void main(String[] args) {
		Scanner userPrincipal = new Scanner(System.in);
		Scanner userRate = new Scanner(System.in);
		Scanner userTime = new Scanner(System.in);
		//interest = principal * rate * time
		double principal, rate, time, interest;
		
		System.out.print("Enter the principal: ");
		try {
		principal = Double.parseDouble(userPrincipal.nextLine());
		}catch(Exception e){
			
		}
		
		System.out.print("Enter the rate: ");
		try{
		rate = Double.parseDouble(userRate.nextLine());
		}catch(Exception e){
			
		}
		System.out.println("Enter the time: ");
		
		try{
			time = Double.parseDouble(userTime.nextLine());
		}catch(Exception e){
			
		}
		
		//interest=calculateInterest(principal,rate,time);
		//System.out.println("Interest: "+interest);

	}
	public static double calculateInterest(double p, double r, double t){
		//interest = principal * rate * time
		return p*r*t;
	}

}
