package com.revature.Q6;

import java.io.IOException;
import java.util.Scanner;

public class Q6 {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		int input = 0;
		System.out.println("Enter an even or odd value: ");
		try{
		input=userInput.nextInt();
		}catch(Exception e){
			System.out.println("Input has to be of int value.");
		}
		System.out.println(isEven(input)); // should return false
	}// main

	public static boolean isEven(int n) {
		if ((n / 2) * 2 == n) {//check if dividing num by 2 and multipling by 2 will equal itself 
			System.out.println(n + " is even.");
			return true;
		} // if
		else {
			System.out.println(n + " is odd.");
			return false;
		}
	}// isEven
}// Q6
