package com.revature.samsel_q4;

/*
 * Program to compute N Factorial
 */
public class FactorialC {
	
	public int Factorial(int iFact){
		
		if(iFact >=1)
			return iFact * Factorial(iFact-1);
		else return 1;
	}
	
	public static void main(String... args){
		
		FactorialC ft= new FactorialC();
	
		//Recursive function 
		int iNum = 5;
		int iFact = ft.Factorial(5);
		System.out.println("Factorial of "+iNum+" : "+iFact );
	}
}
