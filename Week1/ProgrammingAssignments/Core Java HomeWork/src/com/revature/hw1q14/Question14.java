package com.revature.hw1q14;

import java.util.Calendar;
import java.util.Scanner;

public class Question14 {
	
	public static void main(String[] args) {
		
		System.out.println("Enter 1, 2, or 3");
		
		
		try{
		int n = k.nextInt();
		Question14.caseExamp(n);
		}catch(Exception e){
			System.out.println("not a number...");
		}
	}

	static Scanner k = new Scanner(System.in);
	

	public static void caseExamp(int n) {
		switch (n) {
		case 1:
			System.out.println("Enter number to sqrt");
			int sqrt = k.nextInt();
			System.out.println(Math.sqrt(sqrt));
			break;
			
		case 2:
			int d = Calendar.DAY_OF_MONTH;
			int m = Calendar.MONTH;
			int y = Calendar.YEAR;
			System.out.println(m + "/" + d + "/" + y);
			break;
			
		case 3: 
			String arr[] = "I am learning Core Java".split("");
			
			break;
			
		default:
			System.out.println("asasdasdasasd");

		}
	}

}
