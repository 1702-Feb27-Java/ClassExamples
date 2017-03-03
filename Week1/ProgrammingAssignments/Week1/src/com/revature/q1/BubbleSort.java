package com.revature.q1;
/**
 * 
 * @author Aaron Camm
 *
 */
public class BubbleSort {
	public static void main(String[] args){
		int[] array = new int[]{1,0,5,6,3,2,3,7,9,8,4};
		
		sort(array);
		
		
		//prints array;
		for(int i = 0; i < array.length; ++i ){
			System.out.print(array[i] + " ");
		}
		
	}
	
	/**
	 * Sorts the array from lowest to highest 
	 * <br>
	 * Sorts the array utilizing the bubble sort algorithm
	 * @param array 	an int array to be sorted
	 */
	public static void sort(int[] array){
		
		//used as a flag to determine if it needs to go through another pass in the array
		boolean isSorted = false;
	
		
		while (!isSorted){
	
			isSorted = true;
			for(int i = 0; i < array.length - 1; ++i){
				
				//compare each adjacent pair, if out of order swap them, 
				//and causes another pass to occur in first while loop.
				if(array[i] > array[i+1]){
					isSorted = false;
					int temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
				
				
			}
			
			
		}
	}
	
}
