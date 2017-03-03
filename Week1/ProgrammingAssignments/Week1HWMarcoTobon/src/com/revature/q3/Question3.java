package com.revature.q3;

/**
 * 
 * @author tobon
 *	reverses a string without using the StringBuilder,StringBuffer reverse API
 */

public class Question3 {

	public static void main(String[] args) {
		StringBuilder toReverse =new StringBuilder("Eyedea");
		
		int sz = toReverse.length();
		
		//APPEND THE STRING STARTING FROM THE BACK TO ITSELF IE ABC = ABCBA
		for (int k=sz-2; k >= 0; k--)
		{
			toReverse.append(toReverse.charAt(k));
		}
		//DELETE EVERYTHING FROM THE 1 FROM THE BACK ABCBA = CBA
		toReverse.delete(0, sz-1);
		System.out.println(toReverse);
				
	}

}
