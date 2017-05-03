package com.week1homework.question14;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class SwitchCases
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		System.out.println("Showing different switch statements:");
		SwitchStatement(1);
		SwitchStatement(2);
		SwitchStatement(3);

	}
	
	private static void SwitchStatement(int value)
	{
		switch(value)
		{
		case 1:
			System.out.println("Square root of 356,409 is: " + Math.sqrt(356409));
			break;
		case 2: 
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate now = LocalDate.now();
			System.out.println("Today's date is: " + dtf.format(now));
			break;
		}
		case 3:
		{
			String delim = " ";
			String original = "I am learning Core Java";
			String splitString[] = new String[5];
			StringTokenizer st = new StringTokenizer(original, delim);
			
			System.out.println("Unsplit String: " + original);
			
			System.out.println("Split String");
			for (int i = 0; i < 5; i++)
			{
				splitString[i] = (String) st.nextElement();
				System.out.println(splitString[i]);
			}
			
		}
		
		}
	}

}
