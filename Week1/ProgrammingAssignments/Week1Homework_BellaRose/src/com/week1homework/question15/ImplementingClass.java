package com.week1homework.question15;

public class ImplementingClass implements SimpleCalculator
{

	@Override
	public int addition(int arg0, int arg1)
	{
		// TODO Auto-generated method stub
		return arg0 + arg1;
	}

	@Override
	public int subtraction(int arg0, int arg1)
	{
		// TODO Auto-generated method stub
		return arg0 - arg1;
	}

	@Override
	public int multiplication(int arg0, int arg1)
	{
		// TODO Auto-generated method stub
		return arg0 * arg1;
	}

	@Override
	public float division(int arg0, int arg1)
	{
		// TODO Auto-generated method stub
		return (float)(arg0 / arg1);
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
