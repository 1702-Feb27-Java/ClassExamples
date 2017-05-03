package Q8;

import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		StringBuilder test = new StringBuilder();
		String tester;
		String[] strings = {"karan", "madam", "tom", "civic", "radar", "sexes",
				"jimmy", "kayak", "john", "refer", "billy", "did"};
		ArrayList<String> regArray = new ArrayList<String>();
		ArrayList<String> paliArray = new ArrayList<String>();
		
		for(int i = 0; i < strings.length; i++)
		{
			test.append(strings[i]);
			test = test.reverse();
			tester = test.toString();
			
			
			if(strings[i].equals(tester) == true)
			{
				paliArray.add(strings[i]);
			}
			else
			{
				regArray.add(strings[i]);
			}
			
			test.setLength(0);
			tester = "";
		}
		
		System.out.println("Regular Array: " + regArray);
		System.out.println("Palindrome Array: " + paliArray);
	}
}
