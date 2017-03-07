// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 18 - WRITE A PRORGAM HAVING A CONCRETE SUBCLASS THAT INHERITS THREE ABSTRACT METHODS
// FROM A SUPERCLASS. PROVIDE THE FOLLOWING THREE IMPLEMENTATIONS IN THE SUBCLASS CORRESPONDING
// TO THE ABSTRACT METHODS IN THE SUPERCLASS

package com.revature.q18w1;

public class Subclass3Methods extends Abstract3Methods{  // this is the subclass that inherits the abstract methods

	@Override
	public boolean checkUppercase(String s) {
		// TODO Auto-generated method stub
		boolean flag = false;   // this is the flag to see if there are ANY uppercase char
		
		
		for (int i = 0; i < s.length(); i++){
			Character c = new Character(s.charAt(i));   // we have to make a wrapper for the char in the string
			boolean check = c.isUpperCase(s.charAt(i)); // so we can use the .isUpperCase method
			if (check == true){  // the first uppercase char will check the flap to be 'true'
				flag = true;
			}
		}
		
		if (flag == true){  // so if flag is true, then we return true
			return flag;
			}
		else {
			return false;
		}
	}

	@Override
	public String convertToUpper(String s) {  // method to convert all to uppercase in a string
		// TODO Auto-generated method stub
		s = s.toUpperCase(); // just use the method from the string class
		return s;
	}

	@Override
	public void convertStrToInt(String s) {  // method to convert string to int
		// TODO Auto-generated method stub
		Integer i = new Integer(s);  // make a new integer wrapper
		int newInt = i.parseInt(s);  // so we can use the .parseInt method
		int add = newInt + 10;  // we add 10
		System.out.println(add);  // print out the result
		
	}

}
