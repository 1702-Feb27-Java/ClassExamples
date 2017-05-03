package com.revature.hw1q9;

import java.util.*;

public class Question9 {

	private List<Integer> numList = new ArrayList<Integer>();

	public static void main(String[] args) {
		Question9 q9 = new Question9();
		q9.printPrime();
	}

	public Question9() {
		for (int i = 1; i < 101; i++) {
			numList.add(i);
		}
	}

	public void printPrime() {

		boolean isPrime = true;

		for (int i = 2; i < numList.size(); i++) {
			if (numList.get(i) % 2 == 0) {
				continue;
			}
			// will set prime to false and move on to next if they find a
			// multiple
			for (int j = 2; j <= Math.sqrt(numList.get(i)); j++) {
				isPrime = true;
				if (numList.get(i) % j == 0) {
					isPrime = false;
					break;
				}

			}
			// prints only if prime
			if (isPrime)
				System.out.print(numList.get(i) + " ");

		}

	}
}
