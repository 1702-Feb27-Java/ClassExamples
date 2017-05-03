package com.revature.weekone.question9;

import java.util.ArrayList;

/**
 * Displays prime numbers along along a given range.
 * 
 * It uses an ArrayList to hold the numbers from 1-100.
 * 
 * @author Michael Hobbs
 *
 */
public class Question9 {

	/**
	 * Determines whether a number is prime.
	 * 
	 * A prime number is divisible only by itself and 1.
	 * 
	 * @param n the number
	 * @return true if the number is prime.
	 */
	public static boolean isPrime(int n) {
		//if a number is evenly divisible then it is not prime. if it is evenly divisible,
		//a number less than it can evenly divide into it.
		for (int i = 2; i < n; i++) {
			if (n / i * i == n) {
				//integer division; so if result is truly integral (was not truncated) 
				//then n is evenly divisible, thus is not prime.
				return false; //the number is not prime
			}
		}
		return true; //the number is prime
	}
	
	/**
	 * Prints out the prime numbers up to a certain number.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final int numberElements = 100; //the number up to which prime numbers are to be printed; the limit.
		
		// initialize numbers from 1 to the limit.
		ArrayList<Integer> a = new ArrayList<Integer>(); //store the prime numbers up to the limit.
		for (int i = 1; i <= numberElements; i++) { //initialize the store with numbers that are to be checked whether they are prime.
			a.add(i); //store the number.
		}
		
		// print out the numbers from 1 to the limit that are prime.
		for (int i = 0; i < a.size(); i++) {
			if (isPrime(a.get(i))) { //print out the number if is prime.
				System.out.println(a.get(i) + " is prime.");
			}
		}
	}

}
