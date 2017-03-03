package com.revature.samsel_q2;

public class FibonacciC {
	int iLimit;
	public FibonacciC(){this.iLimit = 0;}
	public FibonacciC(int iL){this.iLimit = iL;}
	public void ExecuteF()
	{
		int iTotal=0;
		int i=0,j=1;
		System.out.print(" "+i);
		System.out.print(" "+j);
		while(iTotal<25)
		{
			iTotal = i + j;
			if(iTotal >25)
				break;
			
			System.out.print(" "+iTotal);
			i=j;
			j=iTotal;
		}
	}
}
