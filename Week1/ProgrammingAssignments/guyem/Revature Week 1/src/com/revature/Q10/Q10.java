package com.revature.Q10;

import java.util.Scanner;

public class Q10 {

	public static void main(String[] args) {
		Scanner userinput1 = new Scanner(System.in);
		Scanner userinput2 = new Scanner(System.in);
		//store two numbers of the userinput
		int num1=0;
		int num2=0;
		System.out.println("Enter first number: ");
		//get the first input of the user
		try {
		num1 = userinput1.nextInt();
		}catch(Exception e){
			System.out.println("Unable to read integer value.");
			e.printStackTrace();
		}
		System.out.println("Enter second number: ");
		//get the second input of the user
		try {
		num2 = userinput2.nextInt();
		}catch(Exception e){
			System.out.println("Unable to read integer value.");
			e.printStackTrace();
		}
		System.out.println("Between "+num1+" and "+num2+":"+"("+findMinValue(num1, num2)+")"); 
	}// main

	public static int findMinValue(int a, int b) {
		int minVal = 0;
		minVal = a < b ? a : b; //check if a < b
		return minVal;
	}// findMinValue
}// Q10
