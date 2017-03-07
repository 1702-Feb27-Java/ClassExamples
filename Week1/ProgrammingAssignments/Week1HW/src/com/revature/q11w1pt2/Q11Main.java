// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 11 - WRITE A PROGRAM THAT WOULD ACCESS TWO FLOAT-VARIABLES FROM A CLASS THAT 
// EXISTS IN ANOTHER PACKAGE. NOTE, YOU WILL NEED TO CREATE TWO PACKAGES TO DEMONSTRATE THE SOLUTION.

package com.revature.q11w1pt2;
import com.revature.q11w1pt1.*;

public class Q11Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// we make an object from the class that contains 2 floats
		Contains2Floats floatObj = new Contains2Floats();  
		
		float a = 8.9f;  //examples
		float b = 2.633f;
		
		floatObj.setFloat1(a);  // we set the floats to the object
		System.out.println(floatObj.getFloat1());  // prints them
		
		floatObj.setFloat2(b);
		System.out.println(floatObj.getFloat2());
		
	}

}
