// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 1 - PERFORM A BUBBLE SORT ON THE FOLLOWING INTEGER ARRAY: 1,0,5,6,3,2,3,7,9,8,4

package com.revature.q1wk1;

public class Swap { // we have to write a swap function
	
	public static void swap(int[] list, int a, int b){
		int temp = list[a];  // create a temp
		list[a] = list[b];
		list[b] = temp;
		
		//System.out.println(list[a] + " " + list[b]); // checking if this method works
	}

}
