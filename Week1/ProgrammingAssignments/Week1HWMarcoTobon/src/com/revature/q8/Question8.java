package com.revature.q8;

import java.util.ArrayList;

/**
 * 
 * @author tobon
 * Prints out the palindromes in an ArrayList
 * using the reverse method of string
 */
public class Question8
{
	public static void main(String[] args)
	{
		ArrayList<String> listStrings = new ArrayList<String>();
		listStrings.add("karan");
		listStrings.add("madam");
		listStrings.add("tom");
		listStrings.add("civic");
		listStrings.add("radar");
		listStrings.add("sexes");
		listStrings.add("jimmy");
		listStrings.add("kayak");
		listStrings.add("john");
		listStrings.add("refer");
		listStrings.add("billy");
		listStrings.add("did");
		
		ArrayList<String> palindromes = new ArrayList<String>();
		
		for (String x: listStrings)
		{
			StringBuilder temp = new StringBuilder(x);
			if (x.equals(temp.reverse().toString()))
			{
				palindromes.add(x);
			}
		}
		System.out.println("Palindromes");
		for (String x : palindromes)
		{
			System.out.println(x);
		}
	}

}
