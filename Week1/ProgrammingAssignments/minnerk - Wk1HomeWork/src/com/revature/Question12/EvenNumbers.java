package com.revature.Question12;

public class EvenNumbers {

	public static void main(String[] args) {
		
		//Creating an array of 100 integers
		int array[] = new int[100];
		
		//Loop to enter in numbers 1 to 100 into array
		for (int i = 0; i < 100;i++){
			array[i] = i+1;
		}
		
		//Enhanced for loop to check if element is even, if so displays the element
		for (int i : array){
			if (i%2 == 0){  //Modulus 2 checks for even number
				System.out.println(i);
			}
		}
	}
}
