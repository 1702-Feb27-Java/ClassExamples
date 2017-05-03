package com.week1homework.question10;

public class TernaryCompare
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		System.out.println("Min of 17 and 13 is: " + ternaryMin(17, 13));
		System.out.println("Min of 1 and 9 is: " + ternaryMin(1, 9));
		System.out.println("Min of 42 and 131 is: " + ternaryMin(42, 131));
		System.out.println("Min of -5 and -3 is: " + ternaryMin(-5, -3));
		System.out.println("Min of -17 and 13 is: " + ternaryMin(-17, 13));
		
	}
	
	private static int ternaryMin(int arg0, int arg1)
	{
		return (arg0 < arg1 ? arg0: arg1);
	}

}
