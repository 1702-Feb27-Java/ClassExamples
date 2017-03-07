// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 19 - CREATE AN ARRAYLIST AND INSERT INTEGERS 1 THROUGH 10. DISPLAY THE ARRAYLIST.
// ADD ALL THE EVEN NUMBERS UP AND DISPLAY THE RESULT. ADD ALL THE ODD NUMBERS UP AND DISPLAY
// THE RESULT. REMOVE THE PRIME NUMBERS FROM THE ARRAYLIST AND PRINT OUT THE REMAINING ARRAYLIST.

package com.revature.q19w1;

import com.revature.q6w1.*;  // use method to check for even and odd

import com.revature.q9w1.*;  // use method to check for prime

import java.util.ArrayList;
import java.util.Iterator;

public class Q19Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// we make an arraylist of 1-10
		ArrayList<Integer> arrNum = new ArrayList<Integer>();
		
		int sumOfEven = 0;  // these are counters
		int sumOfOdd = 0;
		
		for (int i = 0; i < 10; i++){
			arrNum.add(i+1);  // we populate the arraylist with numbers 1-10
			
			boolean evenOrOdd = EvenOddCheck.even(i+1);  // this function is imported from Q6
			
			if (evenOrOdd == true){  // that means this number is even
				sumOfEven = sumOfEven + (i+1);  // we add an even number to the total sum
			}
			else {					// that means this number is odd
				sumOfOdd = sumOfOdd + (i+1);  // we add an odd number to the total sum
			}
		}
		System.out.println(arrNum);  // we print out the arraylist of 1-10
		System.out.println("The sum of all the even numbers is: " + sumOfEven);  // sum of all even
		System.out.println("The sum of all the odd numbers is: " + sumOfOdd);  // sum of all odd
		
		Iterator<Integer> itr = arrNum.iterator();  // creates instance of Iterator for my array list
		
		while (itr.hasNext()) {
		    Integer num = itr.next();  // we need the integer wrapper class

		    boolean isPrime = PrimeCheck.isPrime(num);  // checking if current iterator is prime
			
			if (isPrime == true) {  // if it is
				itr.remove();		// remove from the iterator
			}
		}

		System.out.println("The list after removing all prime numbers is: " + arrNum);
		
	}

}
