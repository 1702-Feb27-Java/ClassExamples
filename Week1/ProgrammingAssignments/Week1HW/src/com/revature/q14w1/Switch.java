// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 14 - WRITE A PROGRAM THAT DEMONSTRATES THE SWITCH CASE. IMPLEMENT THE FOLLOWING 
// FUNCTIONALITIES IN THE CASES.

package com.revature.q14w1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Switch {

	// int a is the # of cases and int b is the number we want to find the sqrt of
	public static void switch3cases (int a, int b){ 
		// make a new random object
		Random randNum = new Random();
		int r = randNum.nextInt(a) + 1;  // let's randomize the cases every time we run this method
		switch (r){
		case 1:
			double result = Math.sqrt(b);  // find the sqrt of a number
			System.out.println(result);
			break;
		case 2:
			// prints out the date
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			break;
		case 3:
			
			String s = "I am learning Core Java";
			
			String[] splitArr = s.split(" "); // we split the string into words

			System.out.println(Arrays.toString(splitArr)); // into an array
			break;
			
		default:  // so this will be the message if the user enters > 3 for a and randomizer picks > 3
			System.out.println("You've entered more #s than there are cases and the randomizer didn't pick cases 1-3. Try again! :(");
			break;
		}
	}
}
