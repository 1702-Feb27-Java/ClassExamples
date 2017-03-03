package com.revature.q18;

import java.util.Scanner;

/**
 * 
 * @author tobon
 * Tests our abstract methods from a superclass
 * that are implemented by a concrete subclass
 */
public class Question18test
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		ConcreteSubclass  cS = new ConcreteSubclass();
		System.out.print("Enter a string to see if it contains an Uppercase char: ");
		String test = sc.next();
		System.out.println(cS.checkUppercase(test));
		
		System.out.print("Enter a string to see change its Lowercase to Uppercase chars: ");
		String test2 = sc.next();
		System.out.println(cS.toUpper(test2));
		
		System.out.print("Enter a string to change to int and add 10 to it: ");
		String test3 = sc.next();
		System.out.println(cS.StringToInt(test3));
		sc.close();
	}

}
