package com.revature.q14;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class SwitchDemo {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// testcase test
		int test = 3;
		// test var fed into switch statement
		switch(test){
		// user input double, print square root of input
		case(1):
			Scanner s = new Scanner(System.in);
			System.out.print("Enter number: ");
			double i = s.nextDouble();
			System.out.println(Math.sqrt(i));
			break;
		// print date
		case(2):
			Date d = new Date();
			System.out.println(d.toString());
			break;
		// split string into array then display array
		case(3):
			String inputStr = "I am learning Core Java";
			String strArr[] = inputStr.split(" ");
			System.out.println(Arrays.toString(strArr));
			break;
		}
	
	}

}
