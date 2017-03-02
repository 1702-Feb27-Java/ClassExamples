package com.homework1.question2;

public class FibonacciSequence {

	public static void main(String[] args) {
		fibonnaciAddition(0, 1, 0);
	}
	
	/**
	 * 
	 * 
	 * @param first the left integer being added together
	 * @param second the right integer being added together
	 * @param iteration the number iteration we are on
	 */
	public static void fibonnaciAddition(int first, int second, int iteration){
		//after 25 numbers printed, exit
		if(iteration == 24){
			return;
		}
		//if it is the first iteration print out both numbers
		else if(iteration == 0){
			System.out.print(first + ", " + second);
		}
		//every other iteration, print out the new number
		else{
			System.out.print(", " + second);
		}
		
		int next = first + second;
		iteration++;
		fibonnaciAddition(second, next, iteration);
	}

}
