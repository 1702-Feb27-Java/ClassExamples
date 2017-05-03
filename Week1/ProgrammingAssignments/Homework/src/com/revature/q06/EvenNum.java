package com.revature.q06;
/**
 * This program will show how to check if a number is even with out
 * using the modulus operator
 * @author Nick
 *
 */
public class EvenNum {

	public static void main(String[] args) {
		//declaring int variables
		int num1 = 14;
		int num2 = 38;
		int num3 = 107;
		
		System.out.println(CheckEven(num1));
		System.out.println(CheckEven(num2));
		System.out.println(CheckEven(num3));
		

	}
	
	static int CheckEven( int this_num ) {
		//This function will check if this number is even without -> "%" operator
		int quot = this_num / 2;
		
		//after using the division operator we will multiply it back by to see
		//if it equals the original number
		if ( quot * 2 == this_num ) {
			System.out.print("This number is even: ");
			return this_num; 
		}
		else {
			System.out.print("This number is not even: ");
		    return this_num;
		}
		
	}

}
