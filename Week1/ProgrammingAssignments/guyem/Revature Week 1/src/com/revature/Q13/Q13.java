package com.revature.Q13;

public class Q13 {

	public static void main(String[] args) {
		printStatement(); //print the tree integers
	}//main

	public static void printStatement() {
		// declare primitives to hold values 0 and 1
		int a = 0;
		int b = 1;
		int c = 0;
		boolean check = false;
		for (int i = 0; i < 4; i++) {
			c++; //increment column
			for (int j = 1; j <= c; j++) {
				// check if the condition is false
				if (check == false) {
					System.out.print(a + " ");
					check = true;
				} else {
					check = false;
					System.out.print(b + " ");
				}
				// if the index is equal to the count
				if (j == c) {
					System.out.println();
				}//if
			}//for(j)
		}//for(i)
	}//printStatement
}//Q13
