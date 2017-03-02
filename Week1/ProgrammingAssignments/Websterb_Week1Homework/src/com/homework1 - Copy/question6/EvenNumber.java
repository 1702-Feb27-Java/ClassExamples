package com.homework1.question6;

public class EvenNumber {

	public static void main(String[] args) {
		System.out.println(isEven(20));
	}

	/**
	 * 
	 * @param number the number to check if it is even or odd
	 * @return true or false based on if number is even or not
	 */
	public static boolean isEven(int number){
		//divide the number by 2 and then multiply by 2 and if it is the same, then there was no quotient
		int newNumber = number/2;
		if(newNumber*2 == number){
			return true;
		}
		else{
			return false;
		}
	}
	
}
