package com.revature.Question18;

import java.util.Scanner;

public class TestStringManip {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in); //Creating input object
		System.out.println("Enter in a string: "); //User Prompt
		String str = keyboard.nextLine(); //Getting input string
		keyboard.close(); //Closing keyboard object stream
		
		StringMain sm = new StringManip(); //Creating a StringManip object
		
		//Three different print values
		System.out.println("To UpperCase: " + sm.toUpperCase(str));
		System.out.println("Any UpperCase Letters in String: " + sm.isUpperCase(str));
		System.out.println("String to Int + 10: " + sm.toInt(str));
	}

}
