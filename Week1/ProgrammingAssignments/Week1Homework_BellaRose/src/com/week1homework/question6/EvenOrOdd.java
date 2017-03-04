package com.week1homework.question6;

public class EvenOrOdd
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		System.out.println("Numbers to test if even: 6, 19, 217, 300, 42, 69, 2, 0, 1, -6, -39");
		System.out.println("Is 6 even?: " + isEven(6));
		System.out.println("Is 19 even?: " + isEven(19));
		System.out.println("Is 217 even?: " + isEven(217));
		System.out.println("Is 300 even?: " + isEven(300));
		System.out.println("Is 42 even?: " + isEven(42));
		System.out.println("Is 69 even?: " + isEven(69));
		System.out.println("Is 2 even?: " + isEven(2));
		System.out.println("Is 0 even?: " + isEven(0));
		System.out.println("Is 1 even?: " + isEven(1));
		System.out.println("Is -6 even?: " + isEven(-6));
		System.out.println("Is -39 even?: " + isEven(-39));

	}
	
	private static boolean isEven(int value)
	{
		
		if (value < 0)
			value *= -1;
		
		while(value != 0 && value != 1)
		{
			value -= 2;
		}
		
		if (value == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
