package com.revature.q04;
import java.util.Scanner;
/**
 * Program that shows the factorial of that number
 * @author Nick
 *
 */
public class Factorial {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter your number for the factorial: ");
		int num = keyboard.nextInt();
		
		int fact = 1;
		
		//we start at one and increment up to the number that was passed in
		for ( int i = 1; i <= num; i++) {
			fact = fact * i;
		}
		
		keyboard.close();

		System.out.println(fact);
	}

}
