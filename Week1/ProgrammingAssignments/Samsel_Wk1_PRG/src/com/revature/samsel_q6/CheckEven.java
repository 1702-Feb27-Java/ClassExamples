package com.revature.samsel_q6;

import java.util.Scanner;

/*
 * Program to check if given number is Even or Odd without using modulus (%) operator
 */
public class CheckEven {
	
	public static boolean checkIfEven(int iNum){		
		return ((iNum/2)*2)==iNum?true:false;
	}

	public static void main(String[] args) {
		
		System.out.println("====Number is [Even / Odd]========");
		System.out.print("Please enter a number : ");
		Scanner scanner = new Scanner(System.in);
		String nNum = scanner.next();
	    int iInput = Integer.parseInt(nNum);
	    
	    boolean bStatus = CheckEven.checkIfEven(iInput);
	    if(bStatus==true)
	    	System.out.println("Number is even");
	    else
	    	System.out.println("Number is odd");
	}

}
