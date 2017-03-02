package com.homework1.question9;

import java.util.ArrayList;

public class PrimeNumbers {

	public static void main(String[] args) {
		//create an arrayList for all numbers 1 to 100
		ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
		
		//add the numbers to the array list
		for(int i = 1; i < 101; i++){
			primeNumbers.add(i);
		}
		
		//0 and 1 are not prime
		for(int x = 1; x < primeNumbers.size(); x++){
			//get the first number to check if prime
			int z = primeNumbers.get(x);
			
			boolean prime = true;//assume it is prime to begin with
			
			for(int t = 2; t < z; t++){
				if(z%t == 0){//if the number is divisible by anything then it is not prime
					prime = false;
				}
			}
			if(prime == true){
				System.out.println(primeNumbers.get(x));
			}
		}
	}

}
