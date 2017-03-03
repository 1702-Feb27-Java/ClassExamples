package com.revature.Q19;

import java.util.ArrayList;

public class Q19 {

	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		// insert integers 1 - 10
		for (int i = 1; i <= 10; i++) {
			numbers.add(i);
		}
		// display the array list
		System.out.println(numbers);

		// display even numbers of array list
		System.out.println(getEvenNumbers(numbers));

		// display sum of even numbers
		System.out.println(addEvenNumbers(numbers));

		// display odd numbers of array list
		System.out.println(getOddNumbers(numbers));

		// display sum of odd numbers
		System.out.println(addOddNumbers(numbers));

		// display prime numbers
		System.out.println(getPrimeNumbers(numbers));

	}

	public static ArrayList<Integer> getEvenNumbers(ArrayList<Integer> n) {
		ArrayList<Integer> evenList = new ArrayList<Integer>();
		for (int i = 0; i < n.size(); i++) {
			if (n.get(i) % 2 != 0) {// check if value is even
				evenList.add(i);
			}
		}
		return evenList;
	}

	public static int addEvenNumbers(ArrayList<Integer> n) {
		ArrayList<Integer> evenList = new ArrayList<Integer>();
		int sum = 0;
		for (int i = 0; i < n.size(); i++) {
			if (n.get(i) % 2 != 0) { // check if value is even
				evenList.add(i); //add to the even list
				sum = sum + i; // add to the sum
			}
		}
		return sum;
	}

	public static ArrayList<Integer> getOddNumbers(ArrayList<Integer> n) {
		ArrayList<Integer> oddList = new ArrayList<Integer>();
		for (int i = 0; i < n.size(); i++) {
			if (n.get(i) % 2 == 0) { //check if value is odd
				oddList.add(i); //add to the odd list
			}
		}
		return oddList;
	}

	public static int addOddNumbers(ArrayList<Integer> n) {
		ArrayList<Integer> oddList = new ArrayList<Integer>();
		int sum = 0;
		for (int i = 0; i < n.size(); i++) {
			if (n.get(i) % 2 == 0) { //check if value is odd
				oddList.add(i);
				sum = sum + i; //add up odd numbers
			}
		}
		return sum;
	}

	public static ArrayList<Integer> getPrimeNumbers(ArrayList<Integer> n) {
		ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
		for (int i = 1; i < n.size(); i++) {
			boolean isPrime = true;
			for(int j=2; j<i;j++){ 
				if (i % j == 0) { // check if even
					isPrime=false;
					break;
					
				}
			}
			if(isPrime && i !=1){ // if true and the index is not equal to 1
				primeNumbers.add(i); //add to the prime list
			}
			}
		return primeNumbers;
	}
}
