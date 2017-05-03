package com.revature.q02;
/**
 * Program that simulates the use of the first 25 numbers in the fibonacci sequence
 * @author Nick
 *
 */
public class FiboNumbers {

	public static void main(String[] args) {
		
		int num1 = 0; //first number in sequence
		int num2 = 1; //second number in sequence
		int num3;
		int count = 26; 
		
		System.out.print(num1 + " " + num2 + " ");
		
		//using for loop to print the rest will start at 2 - 26 i.e the first 25 numbers
		for ( int i = 2; i < count; i ++ ) {
			
			//take the sum of num1 and num2 and assign it to num3
			num3 = num1 + num2;
			
			//printing out num3
			System.out.print(num3 + " ");
			
			//the value of num2 will be assigned to num1
			num1 = num2;
			
			//the value of num3 (the sum of the first two numbers) will be assigned to num2
			num2 = num3;
		}
		
	}

}
