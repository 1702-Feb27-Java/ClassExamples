package com.week1homework.question18;

public class Subclass extends ParentClass
{

	@Override
	boolean containsUpperCase(String str)
	{
		// TODO Auto-generated method stub
		
		boolean hasUpper = false;
		char[] arr = new char[str.length()];
		arr = str.toCharArray();
		
		for(int i = 0; i < arr.length; i++)
		{
			char b = arr[i];
			b = Character.toUpperCase(b);
			
			if(arr[i] == b)
			{
				hasUpper =  true;
			}
			else
			{
				hasUpper = hasUpper;
			}
		}
		return hasUpper;
		
	}

	@Override
	String turnIntoUpper(String str)
	{
		// TODO Auto-generated method stub
		return str.toUpperCase();
	}

	@Override
	int addTenToString(String str)
	{
		// TODO Auto-generated method stub
		
		int result = 0;
		for(int i = 0; i < str.length(); i++)
		{
			char a = str.charAt(i);
			result += (int)a;
		}
		
		return result + 10;
		
		
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		

	}

}
