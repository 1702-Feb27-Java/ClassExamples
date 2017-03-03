// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 18 - WRITE A PRORGAM HAVING A CONCRETE SUBCLASS THAT INHERITS THREE ABSTRACT METHODS
// FROM A SUPERCLASS. PROVIDE THE FOLLOWING THREE IMPLEMENTATIONS IN THE SUBCLASS CORRESPONDING
// TO THE ABSTRACT METHODS IN THE SUPERCLASS

package com.revature.q18w1;

public class ImplAbstractNow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 3 examples that checks for each method
		String ex1 = "HeY you there";
		String ex2 = "hEy hi ho HEY";
		String ex3 = "1234";
		
		// creates an object from the subclass that implements the abstract class
    	Subclass3Methods subEx = new Subclass3Methods(); 
    	
    	// let's check the first method
     	System.out.println(subEx.checkUppercase(ex1));
     	
     	// checks the second method
     	ex2 = subEx.convertToUpper(ex2);
     	System.out.println(ex2);
     	
     	// check the third method
     	subEx.convertStrToInt(ex3);
		
	}

}