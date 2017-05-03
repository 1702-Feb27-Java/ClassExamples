package com.revature.Question01;

public class Main {

	//Main method
	public static void main(String[] args) {
		
		//Declaration of array
		int[] myInts = {1,0,5,6,3,2,3,7,9,8,4};
		
		//Creating instance of bubble sort and calling the function to sort it
		BubbleSort mySort = new BubbleSort();
		mySort.sort(myInts);
		
		//Printing loop for the array in the sorted order
		for (int i = 0; i < myInts.length; i++){
			System.out.println(myInts[i]);
		}
	}
}
