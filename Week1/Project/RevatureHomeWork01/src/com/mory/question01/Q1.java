/*Mory Keita
 * Revature Core Java Questions
 * */

package com.mory.question01;

import java.util.Arrays;

public class Q1 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(bubbleSort(new int[] { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 })));

	}
/***
 * 
 * the bubbleSortMethod takes an integer array as
 * parameter and return it sorted.
 * </p>
 *The method is implemented by using two variables for
 *the indexes, and swap the values at those index accordingly.
 *
 * @param Arrays of numbers to bubbleSort
 * @return sorted array
 */
	
	public static int[] bubbleSort(int[] numbers) {
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] > numbers[j]) {
					int temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
				}
			}
		}
		return numbers;

	}

}
