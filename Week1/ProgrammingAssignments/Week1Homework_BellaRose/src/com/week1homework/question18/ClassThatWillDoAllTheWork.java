package com.week1homework.question18;

public class ClassThatWillDoAllTheWork
{

	public static void main(String[] args)
	{
		Subclass main = new Subclass();
		String testStr = "Charmander is the best";
		System.out.println("The original string: " + testStr);
		
		System.out.println("Is there an uppercase letter in the string?: " + main.containsUpperCase(testStr));
		System.out.println("The string in CAPS: " + main.turnIntoUpper(testStr));
		System.out.println("Adding 10 to converted string to get: " + main.addTenToString(testStr));
	}

}
