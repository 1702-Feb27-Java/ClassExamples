package com.revature.q09;

import java.util.ArrayList;
/**
 * This program will check an ArrayList of Integers and
 * prints out the prime numbers to the console
 * @author Nick
 *
 */
public class PrimeNumbers {

	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		PrimeCheck(numbers);
	
	}
	/**
	 * Prime check function that accepts an ArrayList and adds the prime numbers to ArrayList
	 * This function returns nothing
	 * @param num ArrayList of Integer
	 */
	static void PrimeCheck(ArrayList<Integer> num ){
		
		
		//for loop to add the numbers to the ArrayList
		for ( int i = 1; i <= 100; i++) 
			num.add(i);
		
		//for loop to check and print prime numbers  	
		for ( int i = 0; i < num.size(); i++) {
			if ( CheckPrime(num.get(i)))
			    System.out.print(num.get(i) + " ");
		}
	
		
	}
	/**
	 * This function will check if a number is a prime or not
	 * @param n int number to passed in to the function
	 * @return either true or false
	 */
	static boolean CheckPrime(int n) {
		if ( n == 1 ) return false;
		//starting at 2 since 2 is divisible by 1 and it self
		for( int i = 2; i <= n / 2; i++ ) {
            if( n % i == 0)
                return false;
        }
        return true;
	}
	

}
