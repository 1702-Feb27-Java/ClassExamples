package com.revature.Q9;

import java.util.ArrayList;

public class Q9 {

	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<Integer>(); //array to store numbers
		ArrayList<Integer> primeNumbers = new ArrayList<Integer>(); //array to store prime numbers
		//add numbers to ArrayList
		for(int i=0; i<=100;i++){
			numbers.add(i);
		}
		//loop through the ArrayList
		for(int j=0; j<numbers.size();j++){
			//check if numbers are prime
			if(j%2!=0){
				//number is not prime
				primeNumbers.add(j);
			}
		}
		//print prime numbers
		System.out.println(primeNumbers);
	}

}
