package com.revature.Q1;

public class Q1 {

	public static void main(String[] args) {
		// array of size 12
		int[] arr = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		try{
			printArray(arr); // print int array
		}catch(Error e){
			System.out.println("Invalid parameter given.");
			System.out.println("Must be an argument type of an array.");
			e.printStackTrace();
		}
		try{
		bubbleSort(arr); // bubbleSort method
		}catch(Exception e){
			System.out.println("Invalid parameter given.");
			System.out.println("Must be an argument type of an array.");
			e.printStackTrace();
		}
	}// main

	public static void bubbleSort(int[] arr) {
		int n = arr.length; // length of the array
		int temp = 0; // space in memory to store the number to swap
		System.out.println("Bubble Sorted Array: ");
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1] > arr[j]) { // if the value in the next index is
											// greater than previous
					// swap values in indexes
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				} // if
			} // for(j)
			System.out.print(arr[i] + " ");
		} // for(i)
	}// bubbleSort

	public static void printArray(int[] arr) {
		System.out.println("Unsorted Array:");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " "); // print the index of the array
		}
		System.out.println();
	}//printArray
}// Q1
