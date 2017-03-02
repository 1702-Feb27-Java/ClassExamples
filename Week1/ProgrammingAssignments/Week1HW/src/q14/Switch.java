package q14;

import java.time.LocalDateTime;

public class Switch {

	public static void main(String[] args) {
		// use this to set the case 
		int x = 3;
        // switch statement uses x to determine what statement to run
        switch (x) {
            case 1:  System.out.println(" the square root of 100 is : " + Math.sqrt(100)); // computes the square root
                     break;
                     
            case 2: LocalDateTime timePoint = LocalDateTime.now(); //uses java 8's new date class to get the date/time
            		System.out.println(timePoint);
            		break;
            		
            case 3: String str = "I am learning Core Java"; 
            		String arr[] = str.split(" "); //stores string in array with words seperated by a space
            		for (String temp : arr){ // prints out the array to show the original string was split up
            			System.out.println(temp);
            		}
        }
		

	}

}
