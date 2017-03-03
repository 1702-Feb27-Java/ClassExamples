package com.revature.Q12;

import java.util.ArrayList;

public class Q12 {

	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<Integer>(); //array to store numbers
		ArrayList<Integer> evenNumbers = new ArrayList<Integer>(); //array to store even numbers
		for (int i = 0; i <= 100; i++) {
			numbers.add(i); //add value at index to the array
		}
		for (int j = 0; j <= numbers.size(); j++) {
			if (j % 2 == 0) {
				evenNumbers.add(j); //add value at index to the array
			}
		}
		System.out.println("Even ArrayList of Numbers: ");
		for (int index : evenNumbers)
			System.out.print(index + " "); //print even numbers
	}//main
}//
