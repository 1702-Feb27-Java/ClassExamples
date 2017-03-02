package com.homework1.question17;

import java.util.Scanner;

public class Interest {

	public static void main(String[] args) {
		//create a scanner to read user input
		Scanner scanner = new Scanner(System.in);
		double principal, rate, time;
		
		//request principal amount and scan user input
		System.out.print("Enter principal amount: ");
		principal = scanner.nextDouble();
		
		//request rate and scan user input
		System.out.print("Enter rate in percent: ");
		rate = scanner.nextDouble();
		rate = rate/100;
		
		//request time in years and scan user input
		System.out.print("Enter time in years: ");
		time = scanner.nextDouble();
		
		//calculate interest by multiplying the 3 items together
		System.out.println("Interest = principal x rate x time: $" + principal * rate * time);
		scanner.close();
	}

}
