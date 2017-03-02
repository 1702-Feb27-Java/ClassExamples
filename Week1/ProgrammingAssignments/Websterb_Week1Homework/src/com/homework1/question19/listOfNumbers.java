package com.homework1.question19;

import java.util.ArrayList;
import java.util.Arrays;

public class listOfNumbers {

	public static void main(String[] args) {
		//create Integer arraylist
		ArrayList<Integer> list = new ArrayList<>();
		//add 1 through 10 to the array list
		list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		
		//display array list
		System.out.print("Start list: ");
		for(int i = 0; i < list.size(); i++){
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		
		//add all even numbers and display the result
		int evenSum = 0;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i)%2 == 0){
				evenSum += list.get(i);
			}
		}
		System.out.println("Even number sum: " + evenSum);
		
		//add all odd numbers and display the result
		int oddSum = 0;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i)%2 != 0){
				oddSum += list.get(i);
			}
		}
		System.out.println("Odd number sum: " + oddSum);
		
		//0 and 1 are not prime, remove primes and print the remaining array list
		for(int x = 1; x < list.size(); x++){
			//get the first number to check if prime
			int z = list.get(x);
			
			boolean prime = true;//assume it is prime to begin with
			
			for(int t = 2; t < z; t++){
				if(z%t == 0){//if the number is divisible by anything then it is not prime
					/*System.out.print("Z:" + z + " T:" + t + " ");*/
					prime = false;
				}
			}
			if(prime == true){
				list.remove(x);//remove if number is found to be prime
				x--;//we decrease the array by size one so we want don't want to increment
			}
		}
		
		//print out remaining non prime numbers in array list
		System.out.print("Non prime list: ");
		for(int x = 0; x < list.size(); x++){
			System.out.print(list.get(x) + " ");
		}
		
		
		
		
		
		
		
		
		
	}

}
