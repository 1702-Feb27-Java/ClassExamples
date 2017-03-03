package com.revature.samsel_q10;

import java.util.Scanner;

/*
 * Question : Find the minimum of two numbers using ternary operators.
 */

public class MinimumC {
	
	public static int MinOf(int a, int b){
		return a<b?a:b;
	}

	public static void main(String[] args) {
		
		//Get User Input 
		Scanner sInput = new Scanner(System.in);
		System.out.println("Enter 2 numbers : ");
		int a = sInput.nextInt();
		int b = sInput.nextInt();
		
		//Displays minimum number 
		System.out.println("Minimum is :"+MinimumC.MinOf(a, b));
	}

}
