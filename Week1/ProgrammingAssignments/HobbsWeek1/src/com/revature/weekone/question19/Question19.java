package com.revature.weekone.question19;

import java.util.ArrayList;

import com.revature.weekone.question9.Question9;

/**
 * Manages a list of arbitrary length containing numbers from 1 to length.
 * 
 * @author Michael Hobbs
 *
 */
public class Question19 {
	
	ArrayList<Integer> list; //stores the numbers from 1 to length.
	
	/**
	 * Initializes the list to have the given length and values from 1 - length.
	 * 
	 * @param length the number of numbers in the list
	 */
	public Question19(int length) {
		list = new ArrayList<>();
		
		for (int i = 1; i <= length; i++) { //run through the range of numbers to add them to the list individually
			list.add(i); //add the number to the list
		}
	}
	
	/**
	 * Prints the list.
	 * 
	 */
	public void printList() {
		System.out.println("PRINTING THE ENTIRE LIST:");
		System.out.println("LIST SIZE: " + list.size());
		for (int i : this.list) { //run through all the numbers in the list to print them out
			System.out.println(i); //print out the number
		}
	}
	
	/**
	 * Prints the sum of just the even numbers of the list.
	 * 
	 */
	public void printEvenSum() {
		System.out.println("PRINTING THE SUM OF THE EVEN NUMBERS OF THE LIST: ");
		int evenSum = 0;
		for (int i : this.list) {
			if (i % 2 == 0) {
				evenSum += i;
			}
		}
		System.out.println(evenSum);
	}
	
	/**
	 * Prints the sum of just the odd numbers of the list.
	 * 
	 */
	public void printOddSum() {
		System.out.println("PRINTING THE SUM OF THE ODD NUMBERS OF THE LIST: ");
		int oddSum = 0;
		for (int i : this.list) {
			if (i % 2 == 1) {
				oddSum += i;
			}
		}
		System.out.println(oddSum);
	}
	
	/**
	 * Removes the prime numbers and prints out the resulting list.
	 * 
	 */
	public void removePrime() {
		System.out.println("REMOVING PRIME NUMBERS FROM THE LIST:");
		System.out.println("CURRENT LIST:");
		printList(); //print out the list before removing prime numbers
		Integer[] listCopy = new Integer[this.list.size()]; //make a copy of the numbers so we can safely remove them while iterating over the original list
		for (int i = 0; i < listCopy.length; i++) {
			listCopy[i] = this.list.get(i);
		}
		
		for (int i : listCopy) {
			this.list.removeIf((integer) -> Question9.isPrime(integer)); //remove the number from the original list if it is a prime number
		}
		System.out.println("REMOVED PRIME NUMBERS FROM THE LIST:");
		System.out.println("RESULTING LIST:");
		printList(); //print out the list after removing prime numbers
	}

	/**
	 * Initializes a list with numbers from 1 - 10 and invokes all methods to test the class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Question19 list = new Question19(100);
		
		// Test printing the entire list.
		list.printList();
		
		// Test printing the sum of just the even numbers within the list.
		list.printEvenSum();
		
		// Test printing the sum of just the odd numbers within the list.
		list.printOddSum();
		
		// Test printing the removal of the prime numbers from the list.
		list.removePrime();
		
	}

}
