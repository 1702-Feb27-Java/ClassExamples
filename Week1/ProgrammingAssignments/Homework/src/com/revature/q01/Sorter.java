package com.revature.q01;
/**
 * Program that simulates the use of bubble sort on an array
 * @author Nick
 *
 */
public class Sorter {

	public static void main(String[] args) {
		
		//integer array with content in it
		int[] nums = {1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4};
		
		//Running Bubble sort function
		Bubble_Sort(nums);
		
	}
	
	//Bubble Sort function that takes an array of ints
	static void Bubble_Sort( int[] arr) {
		boolean fixed = false;
		int temp = 0;
		
		while( fixed == false ) {
			fixed = true;
			
			//using for loop to keep track of indexes
			for ( int i = 0; i < arr.length - 1; i++) {
				if ( arr[i] > arr[i+1] ) {
					temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
					//if this if statement is accessed, array is not sorted, will keep running
					fixed = false;
				}
			}
		}
		for ( int i = 0; i < arr.length; i++ )
			System.out.print(arr[i] + " ");
				
	}

}
