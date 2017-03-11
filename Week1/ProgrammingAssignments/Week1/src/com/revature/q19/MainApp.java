package com.revature.q19;

import java.util.ArrayList;
/**
 * 
 * @author Aaron Camm
 *
 */
public class MainApp {
	public static void main(String[] args){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		//fill ArrayList with integers from 1 to 10.
		for(int i = 1; i <= 10; ++i){
			list.add(i);
		}
		
		System.out.println("ArrayList: " + list);
		
		int sumOfEvenNumbers=0;
		int sumOfOddNumbers=0;
		
		
		for(Integer i : list){
			// For each integer, check if even and if so add it to sumOfEvenNumbers,
			// else add it to sumOfOddNumbers
			
			if(i % 2 == 0){
				sumOfEvenNumbers += i;
			} else {
				sumOfOddNumbers += i;
			}
		}
		
		for (int i = list.size() - 1; i >= 0; --i){
			if (isPrime(list.get(i))){
				list.remove(i);
			}
		}
		
		
		System.out.println("The sum of even numbers is: " + sumOfEvenNumbers);
		System.out.println("The sum of odd numbers is: " + sumOfOddNumbers);
		System.out.println("ArrayList of Non-prime numbers: " + list);
	}
	
	
	/**
	 * check if a number given is prime
	 * @param n an integer to check for prime. Constrant: 0 < n <= 100
	 * @return true if n is prime, false otherwise
	 */
	public static boolean isPrime(int n){
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
	
	

}
