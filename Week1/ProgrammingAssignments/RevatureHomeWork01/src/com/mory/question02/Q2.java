package com.mory.question02;

// MOry Keita

import java.util.Arrays;

public class Q2 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(fibNumbers(25)));

	}
	/***
	 * The FibNumbers( int upperBound) takes an upper bound
	 * and return an Array of Fibonnaci numbers the maximum
	 * of which is the specified upper bound.
	 * </p>
	 * 
	 * The method is dynamically implemented. We store the values of
	 * the first few Fibonnaci numbers and from these values generate
	 * other Fibonnaci numbers.
	 * </p>
	 * This implementation is more efficient than the recursive method.
	 * 
	 * @param upperBound refers to the the upper bound of
	 * Fibonnaci sequence we want want to generate.
	 * @return return the first Fibonnaci numbers in an array
	 */

	public static int[] fibNumbers(int upperBound) {
		int[] fibArray = new int[upperBound];
		fibArray[0] = 0;
		fibArray[1] = 1;
		fibArray[2] = 2;
		for (int i = 3; i < fibArray.length; i++) {
			fibArray[i] = fibArray[i - 1] + fibArray[i - 2];

		}

		return fibArray;

	}

}

// I could have used recursion but the tin