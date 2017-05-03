package com.revature.Question19;

import java.util.ArrayList;

public class ListPrints {

	public static void main(String[] args) {
		//Creating the Array List		
		ArrayList <Integer>al = new ArrayList<Integer>();
		
		//Entering in 1 to 10 into the ArrayList al
		for (int i = 1; i <= 10;i++){
			al.add(i);
		}
		
		//Printing out the ArrayList to the screen
		System.out.println("The original ArrayList 1 to 10: ");
		for (int i = 0; i < 10;i++){
			System.out.println(al.get(i));
		}
		
		//Declaring an integer to total up the even numbers
		int totalEven = 0;
		
		//Starting at zero and adding up each even element of al ArrayList
		System.out.println("\nEven numbers total: ");
		for (int j = 1; j <= al.size();j+=2){
			totalEven += al.get(j);			
		}
		
		//Printing out the total of the even numbers
		System.out.println(totalEven);
		
		//Declaring an integer to total up the odd numbers
		int totalOdd = 0;
		
		//Starting at zero and adding up each odd element of al ArrayList 
		System.out.println("\nOdd numbers total: ");
		for (int k = 0; k < al.size();k+=2){
			totalOdd += al.get(k);			
		}
		
		//Printing out the total of the odd numbers
		System.out.println(totalOdd);
		
		//Removing the prime numbers from the Array
		al.remove(1);
		al.remove(1);
		al.remove(2);
		al.remove(3);
		
		//Printing the array elements that are left after the remove
		System.out.println("\nArrayList after prime deletes: ");
		for(Integer i:al){
			System.out.println(i);
		}
	}

}
