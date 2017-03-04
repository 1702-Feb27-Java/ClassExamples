package com.week1homework.question8;

import java.util.ArrayList;
import java.util.Collections;

public class PalindromeFinder
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		ArrayList<String> arrList = new ArrayList<>();
		ArrayList<String> palindromes = new ArrayList<>();
		
		arrList.add("karan");
		arrList.add("madam");
		arrList.add("tom");
		arrList.add("civic");
		arrList.add("radar");
		arrList.add("sexes");
		arrList.add("jimmy");
		arrList.add("kayak");
		arrList.add("john");
		arrList.add("refer");
		arrList.add("billy");
		arrList.add("did");
		
		for (int i = 0; i < 12; i++)
		{
			StringBuffer buffer = new StringBuffer(arrList.get(i));
			if (arrList.get(i).equals(buffer.reverse().toString()))
			{
				palindromes.add(arrList.get(i));
			}
		}
		
		System.out.println("All strings: ");
		
		for (int i = 0; i < 12; i++)
		{
			System.out.println(arrList.get(i));
		}
		
		System.out.println(" ");
		
		System.out.println("Palindromes: ");
		
		for (int i = 0; i < palindromes.size(); i++)
		{
			System.out.println(palindromes.get(i));
		}

	}

}
