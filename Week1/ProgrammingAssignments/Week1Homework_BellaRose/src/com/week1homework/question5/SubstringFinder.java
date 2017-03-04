package com.week1homework.question5;

public class SubstringFinder
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		String first = "revature", second = "virginia", third = "java";
		
		System.out.println("First string is revature. Int passed in is 5");
		System.out.println("New substring: " + makeSubString(first, 5));
		System.out.println("Second string is virginia. Int passed in is 3");
		System.out.println("New substring: " + makeSubString(second, 3));
		System.out.println("Third  string is java. Int passed in is 2");
		System.out.println("New substring: " + makeSubString(third, 2));
		
		
	}
	
	private static String makeSubString(String original, int index)
	{
		String substring = "";
		
		for (int i = 0; i <= index - 1; i++)
		{
			substring += original.charAt(i);
		}
		
		return substring;
	}

}
