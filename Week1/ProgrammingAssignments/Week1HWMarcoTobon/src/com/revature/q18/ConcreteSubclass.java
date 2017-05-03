package com.revature.q18;

public class ConcreteSubclass extends AbstractSuper
{
	//CHECKS TO SEE IF THERE IS AN UPPERCASE LETTER IN THE STRING
	/**
	 * @param s 
	 * @return boolean
	 * Returns true if there is an uppercase char in our string
	 */
	public boolean checkUppercase (String s)
	{
		for (int i =0; i < s.length(); i++)
		{
			//CHECKS THE CHARACTER AT I TO SEE IF ITS UPPERCASE
			if (Character.isUpperCase(s.charAt(i)))
			{
				return true;
			}
		}
		return false;
	}
	
	//EVERY CHAR IN THE STRING BECOMES UPPERCASE
	/**
	 *  @param s
	 *  @return String
	 *  Returns a string that is the uppercase of what was passed
	 */
	public String toUpper (String s)
	{
		return s.toUpperCase();
	}
	
	//TURN STRING TO INT AND ADD 10 TO IT
	/**
	 * @param s 
	 * @return int
	 * Returns a string converted to an int + 10
	 * if string contains any char it will instead add the Dec of each one
	 */
	public int StringToInt (String s)
	{
		int result;
		System.out.println("In the method");
		System.out.println(s);
		try
		{
		  result = Integer.parseInt(s);
		  return result+10;
		  
		}
		catch (NumberFormatException e)
		{
			System.out.println("The string you entered is not a number. We will add the Dec value for each character instead");
			char [] letters = s.toCharArray();
			result = 10;
			for (int i =0; i < letters.length;i++)
			{
				result += letters[0];
			}
			return result;
			
		}
		
	}
}
