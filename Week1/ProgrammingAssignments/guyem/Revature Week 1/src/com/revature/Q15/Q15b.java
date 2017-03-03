package com.revature.Q15;

import java.util.Scanner;

public class Q15b implements Q15Interface{

	public static void main(String[] args) {
		Q15b compute = new Q15b(); //object to reference the Q15Interface
		Scanner userinput = new Scanner(System.in);
		int num1=0;
		int num2=0;
		try {
			System.out.println("Enter first number: ");
			//get first value from user
		num1=userinput.nextInt();
		}catch(Exception e){
			System.out.println("Input must be an integer.");
			e.getStackTrace();
		}
		try{
			System.out.println("Enter second number: ");
			//get second value from user
		num2=userinput.nextInt();
		}catch(Exception e){
			System.out.println("Input must be an integer.");
			e.getStackTrace();
		}
		System.out.println("Addition: "+num1+"+"+num2+"="+compute.addition(num1, num2)); 
		System.out.println("Subtraction: "+num1+"-"+num2+"="+compute.subtraction(num1, num2)); 
		System.out.println("Multiplication: "+num1+"*"+num2+"="+compute.multiplication(num1, num2));
		System.out.println("Division: "+num1+"/"+num2+"="+compute.division(num1, num2));

	}

	@Override //the addition override method
	public int addition(int a, int b) {
		return a+b;
	}

	@Override //the subtraction override method
	public int subtraction(int a, int b) {
		return a-b;
	}

	@Override //the multiplication override method
	public int multiplication(int a, int b) {
		return a*b;
	}

	@Override //the division override method
	public double division(double a, double b) {
		return a/b;
	}

}
