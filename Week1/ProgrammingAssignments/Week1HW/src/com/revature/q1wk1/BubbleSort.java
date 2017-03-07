// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 1 - PERFORM A BUBBLE SORT ON THE FOLLOWING INTEGER ARRAY: 1,0,5,6,3,2,3,7,9,8,4

package com.revature.q1wk1;

import java.util.Arrays;

public class BubbleSort {  // this is the class with the bubble sort method

	void sort(int[] array){  // this is the sorting method
		
		int l = array.length; // find the length of the array
		
		for (int i = 0; i < l-1; i++){ // start a for loop to bubble sort based on the length of array
			
			boolean swapped = false; // flag for if adjacent pairs are swapped
			
			for (int j = 0; j < l-1; j++){ // start a second for loop
				
				if (array[j] > array[j+1]){  // loop for sorting adjacent pairs
					Swap.swap(array, j, j+1);  // swap function from the Swap class
					swapped = true;  // flags this iteration as pairs are swapped
				}
				
			}
			
			if (swapped == false){ // if no adjacent pairs are swapped, then that means the list is sorted
				break;  // stop sorting
				
			}
				
		}
		System.out.println(Arrays.toString(array));	// print out the sorted list
	}
	
}
