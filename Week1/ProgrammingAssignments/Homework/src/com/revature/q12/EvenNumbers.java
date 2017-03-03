package com.revature.q12;
/**
 * This program stores numbers into an array
 * and prints out the even numbers using ONLY the enhanced for loop
 * @author Nick
 *
 */
public class EvenNumbers {

	public static void main(String[] args) {
		
		ArrayEvenNumbers();

	}

	/**
	 * Function to store numbers from 1 to 100 in an array and print 
	 * the even numbers
	 * Returns nothing
	 */
	static void ArrayEvenNumbers() {
		//local array declared with a fixed size; nothing inside yet
		int arr[] = new int[100];
		
		//storing the numbers into the array
		for( int i = 0; i < arr.length; i++ ) {
			arr[i] = i + 1;
		}
	
		//Print out the even numbers of array
		PrintEvenNumbers(arr);
	}
	
	/**
	 * Function that prints out the even numbers in an array 
	 * @param arr An array of type int
	 */
	static void PrintEvenNumbers(int arr[]) {
		//using enhanced for loop to print even array elements
		for( int even : arr)
		    if ( even % 2 == 0 )
		    	System.out.print(even + " ");
	}
}
