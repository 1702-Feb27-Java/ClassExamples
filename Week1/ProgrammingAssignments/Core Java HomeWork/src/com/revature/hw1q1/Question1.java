package com.revature.hw1q1;

public class Question1 {

	public static void main(String[] args){
		int arr[] = {1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4};
		
		Question1.bubbleSort(arr, 1);
		
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		
	}
	
	//bubble sort. count used to keep track of position
	
	public static void bubbleSort(int[] arr, int count){
		int temp;
		for(int i = 0; i < arr.length-count; i++){
			if(arr[i] > arr[i+1]){
				temp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = temp;
			}
		}
		count++;
		if(count < arr.length){
			bubbleSort(arr, count);
		}
	}
	
	}
