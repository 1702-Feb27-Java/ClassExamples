package com.revature.weekone.question13;

/**
 * Displays a triangle on the console.
 * 
 * It does not use "a simple group of print statements" in doing so.
 * 
 * @author Michael Hobbs
 *
 */
public class Question13 {
	
	/**
	 * Prints a triangle.
	 * 
	 * A triangle is composed of blocks of 0 and 1.
	 * 
	 * @param levels the number of levels in the triangle
	 */
	public static void printTriangle(int levels) {
		int block = 0; //the initial block in the triangle.
		
		for (int i = 1; i <= levels; i++) { //run for the number of levels in the triangle
			for (int j = 0; j < i; j++) { //print the current level of the triangle
				System.out.print(block + " ");
				block = block < 1 ? block + 1 : 0; //blocks are a sequence of 0101... This sequence spans levels so it must be remembered between iterations.
			}
			System.out.println(); //at end of current level start a new line
		}
	}

	/**
	 * Display a triangle of a certain number of levels.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		final int levels = 4; //the number of levels of the triangle to print out.
		
		Question13.printTriangle(levels); //print out a triangle.
		
	}

}
