package com.revature.samsel_q11b;

import com.revature.samsel_q11a.FloatC;
import com.revature.samsel_q11c.FloatCP;

/*
 *Question : Program to access two float-variables from a class that exists in another package. 
 */

public class FloatMC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FloatC iF = new FloatC(1.0f,22.1f);
		
		System.out.println("==========Two numbers : Different Package==========");
		System.out.println("Value 1 : "+iF.getA());
		System.out.println("Value 2 : "+iF.getB());
		
		System.out.println();
		System.out.println("==========Additional Tests Cases===========");
		//Parent c= 9f; SubClass = 9f
		FloatCP iFP = new FloatCP();
		System.out.println("Return parent Field c value (Explicitly) : "+iFP.getC());
	
		//Dynamic binding & Access modifiers [PROTECTED]
		//PArent va = 3f, va=5f
		FloatC iF1 = new FloatCP();
		System.out.println("FloatC : getC() is not accessible [ protected ] ");
		System.out.println("Variable Shadowing ParentClass  : "+iF1.va);
		System.out.println("Variable Shadowing SubClass     : "+((FloatCP)iF1).va);
	}
}
