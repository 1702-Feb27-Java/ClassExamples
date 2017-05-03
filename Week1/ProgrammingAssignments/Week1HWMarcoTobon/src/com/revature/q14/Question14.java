package com.revature.q14;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 * @author tobon
 * 
 * Demos switch statement with 3 cases
 * 1. Squareroot of a number using method in Math class
 * 2. Display todays date
 * 3. Split string and store it in an array
 */
public class Question14
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		sc.close();
		switch (option)
		{
			//GET THE SQUARE ROOT OF A NUMBER
			case (1):
				System.out.println(Math.sqrt(4));
				break;
			//PRINT OUT THE DATE OF TODAY
			case (2):
				DateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				System.out.println(dt.format(date));
				break;
			//SPLIT STRING AND STORE IT IN A STRING ARRAY
			case (3):
				String testString = "I am learning Core Java";
				String [] splitString= testString.split(" ");
				for (String x : splitString)
				{
					System.out.println(x);
				}
		}			
	}
}
