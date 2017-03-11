/**
 * 
 */
package com.revature.q12;

import java.util.ArrayList;
/**
 * 
 * @author Aaron Camm
 *
 */
public class EvenNumbers {


	public static void main(String[] args) {
		System.out.println(EvenNumbers.getEvenNumbers(100));
		
	}
	
	/**
	 * get Even Numbers from 1 to n 
	 * @param n Max number to return 
	 * @return an ArrayList of even numbers from 1 to n
	 */
	public static ArrayList<Integer> getEvenNumbers(int n){
		ArrayList<Integer> numbers = new ArrayList<>();
		for(int i = 1; i <= n; ++i){
			//checks if i is even
			if (i % 2 == 0){
				numbers.add(i);
			}
		}
		
		return numbers;
	}

}
