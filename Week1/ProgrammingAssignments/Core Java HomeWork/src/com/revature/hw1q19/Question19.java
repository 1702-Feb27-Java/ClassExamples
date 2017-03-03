package com.revature.hw1q19;

import java.util.*;

public class Question19 {
	
	public static void main(String[] args) {
		
		Question19 q19 = new Question19();
		q19.addEven();
		q19.addOdd();
		q19.removePrime();
		
	}
	
	//instantiates array on constructor
	List<Integer> arr = new ArrayList<Integer>();
	
	public Question19(){
		
		for(int i = 1 ; i < 11; i++){
			arr.add(i);
		}
		
	}
	
	public void addOdd(){
		int sum = 0;
		for(int i = 0; i < arr.size(); i++){
			if(arr.get(i)%2 != 0){
				sum += arr.get(i);
			}
		}
		
		System.out.println(sum);
	}
	
	public void addEven(){
		int sum = 0;
		for(int i = 0; i < arr.size(); i++){
			if(arr.get(i)%2 == 0){
				sum += arr.get(i);
			}
		}
		
		System.out.println(sum);
	}
	
	
	//same as print prime except removes prime when found
	public void removePrime(){
		
		boolean isPrime = true;
			
			for(int i = 2; i < arr.size(); i++){
				if(arr.get(i)%2 == 0){
					continue;
				}
				for(int j = 2; j <= Math.sqrt(arr.get(i)); j++){
					isPrime = true;
					if(arr.get(i)%j == 0){
						isPrime = false;
						break;
					}	
	
				}
				//prints only if prime
				if(isPrime)
					arr.remove(i);
				
			}
			arr.remove(1);
			
			System.out.println(arr);
		
	}

}
