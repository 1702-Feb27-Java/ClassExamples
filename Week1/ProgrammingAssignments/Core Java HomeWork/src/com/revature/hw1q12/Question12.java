package com.revature.hw1q12;

public class Question12 {
	
	public static void main(String[] args) {
		Question12 q12 = new Question12();
		q12.printArr();
	}

	//create array
	int[] arr = new int[100];

	//load up values
	public Question12(){
		for(int i = 0; i < arr.length; i++){
			arr[i] = i+1;
		}
	}
	
	//print even ints
	public void printArr(){
		for(int i : arr){
			if(i%2 ==0)
			System.out.print(i + " ");
		}
	}
		
	
}
