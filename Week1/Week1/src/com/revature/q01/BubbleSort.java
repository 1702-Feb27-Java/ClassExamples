package com.revature.q01;

import java.util.Arrays;

public class BubbleSort {
	
	/**
	 * Performs bubble sort on integer array
	 * @param args
	 */
	public static void main(String[] args) {
		// input array
		int[] input = {1,0,5,6,3,2,3,7,9,8,4};
		// create a 'swapped' flag indicating whether or not values were swapped
		// test if i-1 index var is greater than ith var
		boolean swapped = true;
		// reiterate bubble sort if swap occurs
		while(swapped == true){
			// initialize swapped flag to false
			swapped = false;
			// iterate over array
			for(int i = 1; i < input.length; i++) {
				if(input[i-1] > input[i]){
					// swap
					int temp = input[i];
					input[i] = input[i-1];
					input[i-1] = temp;
					// activate swapped flag
					swapped = true;
				}
			}
		// print out arrays
		System.out.println(Arrays.toString(input));
		}
		//

	}

}
