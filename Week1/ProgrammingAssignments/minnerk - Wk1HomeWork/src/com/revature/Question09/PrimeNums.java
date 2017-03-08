package com.revature.Question09;

//Importing ListPrints
import java.util.ArrayList;

public class PrimeNums {

	public static void main(String[] args) {
		//Creating a new integer ListPrints
		ArrayList <Integer> prime = new ArrayList<Integer>();
		
		//Loop to add 1 through 100 to the prime ListPrints
		for (int i = 1; i <= 100; i++)
			prime.add(i);

		//Starting at the first element in prime and checking to see if it is prime
		//and displays it if it is a prime number
		for (int i:prime){
			isPrime(i);
		}
	}
	
	//Method that checks and displays the prime number from 1 to 100
	public static void isPrime(int i){
		if (i == 2 || i == 3) //If it is a 2 or 3 it prints it out
			System.out.println(i);
		if ( i != 1 && i % 2 != 0 && i % 3 != 0){ //All conditions have to be true, in order for the number
			System.out.println(i);				  //to be displayed.
		}
	}
}
