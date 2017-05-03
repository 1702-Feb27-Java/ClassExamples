package com.revature.q6;
/**
 * 
 * @author tobon
 * Checks to see if an integer is even without using modulus
 * turns the int to a stringbuffer and checks the last elements
 * if it is 0,2,4,6,8 then it is even
 */
public class Question6 {
	public static void main (String [] args)
	{
		int x = 1232;
		StringBuffer temp = new StringBuffer(Integer.toString(x));
		char value = temp.charAt(temp.length()-1);
		int  lastValue = Character.getNumericValue(value);
		boolean isEven= false;
		for (int i=0; i <= 8; )
		{
			if (i == lastValue)
			{
				System.out.println("This is even:" + x);
				isEven=true;
			}
				
			i+=2;
		}
		if (!isEven)
		{
			System.out.println("This is odd");
		}
		
		
			
		
		
	}
}
