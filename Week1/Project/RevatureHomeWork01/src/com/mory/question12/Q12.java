package com.mory.question12;

import java.util.*;

public class Q12 {

	public static void main(String[] args) {
		displayList(100);

	}
/***
 * We iterate over number between zero and upper bound.
 * add the even number to a linked list and then
 * print the element in the linked list using
 * and enhanced for loop.
 * 
 * @param upperBound the upper Bound of even numbers to display.
 */

	public static void displayList(int upperBound) {
		List<Integer> list = new LinkedList<>();
		for (int i = 0; i <= upperBound; i++) {
			if (i % 2 == 0)
				list.add(i);
		}
		for(int str:list)
			System.out.println(str);

	}

}
