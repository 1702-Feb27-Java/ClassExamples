package com.revature.samsel_q19;

import java.util.ArrayList;

public class PrintArray {
	
	static boolean IsPrime(int iE){
		//Get Prime numbers
		for(int i=2; i<=iE;i++){
			if((iE%i)==0 && (iE != i))
				return false;
			else
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Integer> ar = new ArrayList();
		for(int i=1;i<=10;i++){
			ar.add(i);
		}
		
		System.out.println("Inputs : "+ar);
		
		//Total of even numbers
		int iTotalEven=0;
		for(int iE : ar){
			if((iE/2)*2==iE){iTotalEven+=iE;}
		}
		
		System.out.println("Total of Even : "+iTotalEven);
		
		//Total of odd numbers
		int iTotalOdd=0;
		for(int iE : ar){
			if((iE/2)*2!=iE){iTotalOdd+=iE;}
		}
		
		System.out.println("Total of Odd : "+iTotalOdd);
		
		//Get Prime
		ArrayList<Integer> iP = new ArrayList();
		for(int iF : ar){
			if(PrintArray.IsPrime(iF)==true)
				iP.add(iF);
		}
		
		System.out.println("Array of Prime Numbers : "+iP);
	}

}
