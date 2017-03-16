package com.revature.q19;

import java.util.ArrayList;

public class NoPrimeArray {

	public static void main(String[] args) {
		ArrayList<Integer> intArr = new ArrayList<>();
		for(int i = 1; i <= 10; i++){
			intArr.add(i);
		}
		// even array
		int evenSum = 0;
		for(int j : intArr){
			if(j % 2 == 0){
				evenSum += j;
			}
		}
		System.out.println(evenSum);
		
		// odd array
		int oddSum = 0;
		for(int k : intArr){
			if(k % 2 == 1){
				oddSum += k;
			}
		}
		System.out.println(oddSum);
		
		// remove primes from intArr
		for(int l = 0 ; l < intArr.size();){
			if(isPrime(intArr.get(l))){
				intArr.remove(l);
			}
			else
				l++;
		}
		// print out intArr with primes removed
		System.out.println(intArr.toString());
	}
	public static boolean isPrime(int p){
		for(int a = 2; a < (int)Math.ceil(Math.sqrt(p)) + 1; a++){
			if(p % a ==  0)
				return false;
		}
		return true;
	}

}
