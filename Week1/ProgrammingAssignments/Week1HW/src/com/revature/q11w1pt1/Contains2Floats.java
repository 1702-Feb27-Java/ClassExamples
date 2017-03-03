// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 11 - WRITE A PROGRAM THAT WOULD ACCESS TWO FLOAT-VARIABLES FROM A CLASS THAT 
// EXISTS IN ANOTHER PACKAGE. NOTE, YOU WILL NEED TO CREATE TWO PACKAGES TO DEMONSTRATE THE SOLUTION.

package com.revature.q11w1pt1;

public class Contains2Floats {

	private float float1;  //  create float var #1
	private float float2;  //  create float var #2
	
	public float getFloat1() {  // getter
		return this.float1;
	}
	
	public void setFloat1(float a) {  // setter
		this.float1 = a;
	}
	
	public float getFloat2() {  // getter
		return this.float2;
	}
	
	public void setFloat2(float a) {  // setter
		this.float2 = a;
	}

}

