package com.revature.q14;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SwitchCase {

	public static void main(String[] args) {
		
		DateFormat this_date = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please enter the number 1,2, or 3: ");
		try {
			
			int number = keyboard.nextInt();
			switch(number){
			
			case 1 : System.out.println("The square root of 25 is "+ (int)Math.sqrt(25) );
				break;
			case 2 : System.out.println(this_date.format(date));
				break;
			case 3 : String[] str = "I am learning Core Java".split(" ");
					 for (int i = 0; i < str.length; i++)
						 System.out.println(str[i]);
				break;
			default:
				System.out.println("You didnt pick 1, 2, or 3!");
					 
			
			}
		} 
		
		catch(Exception e)
		{
			System.out.println("you need to enter a number!!!");
			keyboard.close();
		}
		
		
		
	}

}
