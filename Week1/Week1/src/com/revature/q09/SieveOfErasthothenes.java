package com.revature.q09;
import java.util.*;
public class SieveOfErasthothenes {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// create instance of ArrayList
		ArrayList numList = new ArrayList();
		// add 1 to numList initially
		numList.add(1);
		// loop over numList, add every number
		// use isPrime to test for primes in each iteration and print to console 
		// if test succeeds
		for(int i = 2; i <= 100; i++){
			numList.add(i);
			if(isPrime(i)){
				System.out.print(i + ", ");
			}
		}
	}
	
	/**
	 * 
	 * @param n input number to be checked
	 * @return boolean
	 */
	static boolean isPrime(int n){
		// create a sentinel bound at (square root of n) + 1
		// theres no need to check for primality for divisors past sqrt(n)
		int sentinelBound = (int)Math.sqrt(n) + 1;
		// iterate divisibility check for each number from 2 to input's sentinel bound
		for(int i = 2; i < sentinelBound; i++){
			// use modulus operator to test for divisibility
			if(n % i == 0){
				// return false if there exists divisor as we can immediately
				// conclude that input is not prime
				return false;
			}
		}
		return true;
	}

}
