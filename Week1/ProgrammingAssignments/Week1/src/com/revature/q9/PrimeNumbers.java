package com.revature.q9;

import java.util.ArrayList;
/**
 * 
 * @author Aaron Camm
 *
 */
public class PrimeNumbers {
	
	public static void main(String[] args){
		PrimeNumbers p = new PrimeNumbers();
		System.out.println(p.getPrimes(1000));
	}
	
	/**
	 * check if a number given is prime
	 * @param n an integer to check for prime. Constrant: 0 < n <= 100
	 * @return true if n is prime, false otherwise
	 */
	public boolean isPrime(int n){
		
		//1 is not prime
		if (n == 1){
			return false;
		}
		//2 and 3 are prime.
		else if (n <= 3){
			return true;
		}
		//if divisible by 2 or 3, then not prime
		else if (n % 2 == 0 || n % 3 == 0){
			return false;
		}
		
		// Utilizes trail division to check rest of primes
		// (See: https://en.wikipedia.org/wiki/Primality_test#Simple_methods)
		for(int i = 5; (i*i) <= n; i += 6){
			if ((n % i ) == 0 || (n % i + 2 ) == 0)
				return false;
		}
		return true;
	}
	
	/**
	 * gets all primes from 1 to n
	 * @param n largest number to check for primes
	 * @return ArrayList of all primes from 1 to n
	 */
	public ArrayList<Integer> getPrimes(int n){
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 1; i <= n; ++i){
			if (this.isPrime(i)){
				primes.add(i);
			}
		}
		
		return primes;
	}
	
}

class A {
	 void a(){}
}
class B extends A{
	protected void a(){}
}
