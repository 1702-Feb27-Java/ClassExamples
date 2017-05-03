package com.week1homework.question1;

public class BubbleSort {
	
	private int[] array = {1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BubbleSort bubby = new BubbleSort();
		
		System.out.println("Original Array");
		bubby.showArray();
		
		bubby.sort(bubby.array);
		
		System.out.println("Sorted Array");
		bubby.showArray();
		
	}
	
	private void showArray(){
		
		for(int i = 0; i < array.length; i++){
			
			if (i == array.length - 1){
				System.out.print(array[i] + "\n");
			}
			else{
				System.out.print(array[i] + ", ");
			}
			
			
		}
		
	}
	
	private void sort(int[] numbers){
		
		//We want to find the exact length of the array so we don't step out of bounds
		int n = numbers.length;
		// temporary variable for swapping two elements
        int temp = 0;
       
        //We need a double for loop(One starting at [0] and the other at [1]
        for(int i=0; i < n; i++){
                for(int j=1; j < (n-i); j++){
                       	
                		//check two values to determine if a swap should occur
                        if(numbers[j-1] > numbers[j]){
                                //swapping the elements
                                temp = numbers[j-1];
                                numbers[j-1] = numbers[j];
                                numbers[j] = temp;
                        }
                       
                }
        }
		
	}

}
