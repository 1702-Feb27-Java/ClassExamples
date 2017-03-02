package com.homework1.question1;

public class BubbleSort {


	public static void main(String[] args) {
		int bubbleArray[] = {1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4};
		bubbleSort(bubbleArray, 0);
	}
	
	/**
	 * 
	 * @param bubbleArray the array being sorted
	 * @param count the number of times sorted
	 * @return int[] the sorted array
	 */
	public static int[] bubbleSort(int[] bubbleArray, int count){
		//always start comparing at the left most element of the array
		int x = bubbleArray[0];
		int locationInArray = 1;
		boolean swap = false;
		
		//when the count gets to one less than the length of the array, sorting is done
		if(count > bubbleArray.length - 2){
			return bubbleArray;
		}
		else{
			//compare until the end of array minus the number of times already sorted
			while(locationInArray < bubbleArray.length - count){
				//if the value is greater, move it to the right, then increment
				if(x > bubbleArray[locationInArray]){
					bubbleArray[locationInArray - 1] = bubbleArray[locationInArray];
					bubbleArray[locationInArray] = x;
					locationInArray++;
					swap = true;
				}
				else{
					x = bubbleArray[locationInArray];
					locationInArray++;
				}
			}
			//if you never swap, then it is sorted
			if(swap == false){
				return bubbleArray;
			}
			count++;//increment the count of times swapped, indicating how many elements are sorted
			System.out.println("count: " + count);
			for(int i = 0; i < bubbleArray.length; i++){
				System.out.print(bubbleArray[i] + " ");
			}
			System.out.println();
			return bubbleSort(bubbleArray, count);
		}
	}

}
