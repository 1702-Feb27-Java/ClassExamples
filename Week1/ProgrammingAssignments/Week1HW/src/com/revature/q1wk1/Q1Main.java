// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 1 - PERFORM A BUBBLE SORT ON THE FOLLOWING INTEGER ARRAY: 1,0,5,6,3,2,3,7,9,8,4

package com.revature.q1wk1;

public class Q1Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] myList = {1,0,5,6,3,2,3,7,9,8,4};  // initialize the list
		
		//Swap s = new Swap();  //checking if the swap class/method work
		//int[] list2 = {1,4};
		//s.swap(list2, 0, 1);
		
		BubbleSort sortThis = new BubbleSort(); // creates a new object
		sortThis.sort(myList); // sorting!
		
	}

}
