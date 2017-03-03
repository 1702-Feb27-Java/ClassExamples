package com.revature.samsel_q1;

public class BubbleSortMain {

	public static void main(String[] args) {
		
		//Input Array List to be Sorted
		int[] iArray={1,0,5,6,3,2,3,7,9,8,4};
		int iLengthOfArray = iArray.length;
		
		//Print Array before Sorting
		System.out.println("===============Before Sorting================");
		for(int i=0;i<=iLengthOfArray-1;i++){
		System.out.print(" "+iArray[i]);
		}
		
		//Instance of BubbleSort Class created
		BubbleSort bSort = new BubbleSort();
		bSort.sortArray(iArray); // Sort the Input Array using Bubble Sort Algorithm
		
		//Print the Array after sorting
		System.out.println();
		System.out.println("===============After Sorting================");
		for(int j=0;j<=iLengthOfArray-1;j++){
			System.out.print(" "+iArray[j]);
		}	
	}
}
