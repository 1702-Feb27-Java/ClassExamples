package com.homework1.question12;

public class ArrayOfEvenNumbers {

	public static void main(String[] args) {
		//create an integer array of size 100
		int[] myArray = new int[100];
		
		//fill the array with numbers 1 to 100
		for(int i = 0; i < 100; i++){
			myArray[i] = i + 1;
		}
		//use enhanced for loop to iterate through all numbers of myArray
		for(int number : myArray){
			if(number%2 == 0){//if the number has no remainded when divided by 2 it is even
				System.out.print(number + " ");
			}
		}
	}

}
