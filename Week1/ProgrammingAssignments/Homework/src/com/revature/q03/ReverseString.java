package com.revature.q03;
/**
 * This program demonstrates reversing a string without using the reverse() function
 * @author Nick
 *
 */
public class ReverseString {

	public static void main(String[] args) {
		
		//Declaring a string variable
		String statement = "God, i hate homework!";
		
		//using a for loop starting at the very end of the string, we decrement staring from the last chararcter
		for(int i = statement.length() - 1; i >= 0; i--)
			System.out.print(statement.charAt(i));
		
		}

}
