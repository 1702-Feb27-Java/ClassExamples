package com.mory.question19;

import java.util.*;


public class Q19 {	
	public static void main(String[]ars){
		ArrayList<Integer> list=new ArrayList<Integer>();
		populate(list,10);
		display(list);
		addAllEvenNumbers(list);
		addAllOddNumbers(list);
		removePrimes(list);
		display(list);
		
		
		
		
	}	
	public static ArrayList<Integer> populate(ArrayList<Integer> list,int upperBound) {
		int i = 1;
		while (i <= upperBound) {
			list.add(i);
			i++;
		}
		return list;

	}
	/***
	 * 
	 * @param list the list to display
	 */
	public static void display(ArrayList<Integer> list){
		System.out.println(" The ArrayList current contains the following elements:");
		for(int i:list)
			System.out.print(i+",");
		System.out.println();
		
	}
	
	/***
	 * This methods takes an ArrayList, remove primes numbers
	 * within it and then return the list.
	 * @param ls  the list from which to remove primes
	 * @return the ls, with primes removed.
	 */
	public static ArrayList<Integer>removePrimes(ArrayList<Integer> ls){
		
		for( int i=0; i<ls.size();){
			if(isPrime(ls.get(i))){
				
				ls.remove(ls.get(i));
				
			}
			else{
			i++;
			}
		}
		return ls;
		
	}
	
	public static void addAllOddNumbers(ArrayList<Integer> list){
		int sum=0;
		for(int i:list){
			if(isOdd(i)){
				sum+=i;
			}
		}
		System.out.println("The sum of the  odd elements currently in the array is :"+sum);
	
	}
	/***
	 * 
	 * @param list the list of even numbers to add up.
	 */
	public static void addAllEvenNumbers(ArrayList<Integer> list){
		int sum=0;
		for(int elm:list){
			if(!isOdd(elm)){
				sum+=elm;
			}
		}
		System.out.println("The sum of the  even elements currently in the array is :"+sum); 
		
	}
	
	// This method checks if a number is prime.
	// returns true if it si, false else
	public static boolean isPrime(int num) {
		if (num==2) return true;
		if (num%2==0) return false;
		for (int i = 3; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}
	// this method check if a number is odd.
	// returns true if odd
	public static boolean isOdd(int num){
		return num%2!=0;
	}
	
	
	}


