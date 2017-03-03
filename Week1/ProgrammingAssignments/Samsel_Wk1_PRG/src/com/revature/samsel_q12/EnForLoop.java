package com.revature.samsel_q12;

import java.util.ArrayList;

/*
 * Program to store numbers from 1 to 100 in an array. Print out all even numbers
 * from the array. Use the enhanced FOR loop for printing out the numbers.  
 */

public class EnForLoop {
	
	public static boolean IsEven(int iNum){
		return ((iNum/2)*2)==iNum?true:false;
	}

	public static void main(String[] args) {

		//Populate the Array with 100 integers
		int[] arNum = new int[100];
		for(int j=1;j<=100;j++){
			arNum[j-1]=j;
		}
		
		//Print even numbers from [1-100]
		System.out.println("Even numbers [1-100] are : ");
		for(int i:arNum){
		boolean bIsEven = EnForLoop.IsEven(i);
		if(bIsEven==true)
			System.out.print(" "+i);
		if(i%20==0)
			System.out.println();	//Every 20 operations print newline
		}
	}
}
