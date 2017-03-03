package com.mory.question09;

import java.util.*;

public class Q9 {

	public static void main(String[] args) {
		System.out.println(populatePrime(100));

	}
/***
 * <h1> Basic Idea</h1>
 * two is the only even prime. so we only need
 * to check for odd prime numbers.
 * 
 * 
 * @param num The number which parity we want to check
 * @return true is the number is prime, otherwise return false
 */
	public static boolean isPrime(int num) {
		if (num==2) return true;
		if (num%2==0) return false;
		for (int i = 3; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}
/***
 * We check numbers between  between 2 and upper bound and
 * add the even numbers to a new linkedList which we will return
 * 
 * @param upperBound the upper Bound of prime Numbers to populate. 
 * upper Bound may or may not be inclusive;
 * @return list of primes the maximum of which  is the specified upper bound;
 */
	public static List<Integer> populatePrime(int upperBound) {
		List<Integer> primeList = new LinkedList<>();
		for (int i = 2; i <= upperBound; i++) {
			if (isPrime(i))
				primeList.add(i);
		}

		return primeList;

	}

}
