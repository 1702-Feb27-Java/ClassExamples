package com.revature.samsel_q9;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * Program to print out prime numbers from 1 to 100 on console 
 */
public class ArrayLPrime {

	public static void main(String[] args) {
		
		ArrayLPrime iIsPrime = new ArrayLPrime();
		ArrayList iA = new ArrayList();
		
		System.out.println("===========Prime Numbers [1 - 100]============");
		System.out.println();
		for(int i=1; i<=100;i++)
		{
			boolean bRes = iIsPrime.IsPrime(i);
			if(bRes==true){
				System.out.print(" "+i);
				iA.add(i);
			}
		}
	}

	public boolean IsPrime(int iNum) {
		// TODO Auto-generated method stub
		if(iNum==1)return false;
		
		for(int i=2;i<=iNum;i++){
		if(iNum%i==0 && iNum!=i)return false;
		}
		return true;
	}

}
