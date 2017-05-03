package com.revature.Question01;

public class BubbleSort {
	
	//Method to sort integer array
	public void sort(int[] array){
		
		//Loop that iterates through the numbers 1 through the end of the array - 1 so it does not go past
		//the end of the array bounds
		for (int i = 1; i < array.length-1; i++){
			
			//Main loop that goes through item by item swapping one at a time
			for (int j = 0; j < array.length-1; j++){
				
				//If statement that if true will swap the first with the second item, if not true
				//moves to the next item in the array and compares it
				//continues until array end.
				if (array[j] > array[j+1]){
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}	
	}
}
