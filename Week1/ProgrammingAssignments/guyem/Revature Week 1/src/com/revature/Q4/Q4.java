package com.revature.Q4;

public class Q4 {
	public static void main(String[] args) {
		System.out.println("5!: ");
		try {
			computeFactorial(5); // 5 20 60 120 120
		} catch (Exception e) {
			System.out.println("Invalid parameter given.");
			System.out.println("Must be an argument type int");
			e.printStackTrace();
		}
		System.out.println("8!: ");
		try {
			computeFactorial(8); // 8 56 336 1680 6720 20160 40320
		} catch (Exception e) {
			System.out.println("Invalid parameter given.");
			System.out.println("Must be an argument type int");
			e.printStackTrace();
		}
	}// main

	public static int computeFactorial(int n) {
		int ans = 1; // multiply by 1
		while (n > 0) { // while the number is greater than 0
			ans *= n; // multiply by the number
			System.out.print(ans + " ");
			n--; // decrement the number
		} // while
		System.out.println();
		return ans;
	}// computeFactorial
}// Q4
