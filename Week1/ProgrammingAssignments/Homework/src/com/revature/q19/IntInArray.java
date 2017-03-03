package com.revature.q19;

import java.util.ArrayList;
/**
 * This program will create an ArrayList with integers 1 - 10
 * Display them, add up all the odd and even numbers, display their sum 
 * respectilly and lastly remove the primes and display the remaining list
 * @author Nick
 *
 */
public class IntInArray {

	public static void main(String[] args) {
		
		Compute();

	}
	
	static void Compute() {
		
		ArrayList<Integer> this_list = new ArrayList<Integer>();
		
		//Adding numbers 1 - 10 in array
		for ( int i = 1; i <= 10; i++ ){
			this_list.add(i);
		}
		
		//Printing out the array
		ArrayPrint(this_list);
		System.out.println("");
		//Printing even numbers
		EvenArrayNumDisplay(this_list);
		System.out.println("");
		
		//Printing odd numbers
		OddArrayNumDisplay(this_list);
		System.out.println("");
		
		//Print list with primes removed
		
		RemovePrimeAndDisplayRest(this_list);
		
	}
	
	/**
	 * Function to check where number is a prime or not
	 * @param n is of type int
	 * @return
	 */
	static boolean isPrime(int n) {
		if ( n == 1 ) return false;
		//starting at 2 since 2 is divisible by 1 and it self
		for( int i = 2; i <= n / 2; i++ ) {
            if( n % i == 0)
                return false;
        }
        return true;
	}
	/**
	 * Function to print elements in ArrayList anywhere in this program
	 * @param thisL ArrayList of type Integer
	 */
	static void ArrayPrint(ArrayList<Integer> thisL ) {
		for ( int i = 0; i < thisL.size(); i++ )
			System.out.print(thisL.get(i).toString() + " ");
		
	}
	
	/**
	 * Function to display sum of even numbers of an ArrayList 
	 * @param thisL is of type ArrayList with Integer generic
	 */
	static void EvenArrayNumDisplay(ArrayList<Integer> thisL) {
		int result = 0;
		
		for ( int i = 0; i < thisL.size(); i++ ) {
			if ( thisL.get(i) % 2 == 0 )
				result += thisL.get(i);
		}
		
		System.out.print(result);
	}
	
	/**
	 * Function to display sum of odd numbers of an ArrayList
	 * @param thisL is of type ArrayList with Integer generic
	 */
	static void OddArrayNumDisplay(ArrayList<Integer> thisL) {
		int result = 0;
		
		for ( int i = 0; i < thisL.size(); i++ ) {
			if ( thisL.get(i) % 2 != 0 )
				result += thisL.get(i);
		}
		
		System.out.print(result);
	}
	
	static void RemovePrimeAndDisplayRest(ArrayList<Integer> thisL) {
		ArrayList<Integer> primes = new ArrayList<>();
		
		for ( int i = 0; i < thisL.size(); i++) {
			if ( isPrime(thisL.get(i)) )
			{
				primes.add(thisL.get(i));
					
			}
		}
		for (Integer x : primes)
		{
			thisL.remove(x);
		}
		ArrayPrint(thisL);
	}

}
