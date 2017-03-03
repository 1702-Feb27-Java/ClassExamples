package com.revature.samsel_q8;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Question Program to store the initialized strings in an ArrayList and saves all the palindrome
 * in another ArrayList
 */

public class ArrayLC {

	public static void main(String[] args) {
		
		String[] str = {"karan","madam","tom","civic","radar",
						"sexes","jimmy","kayak","john","refer","billy","did"};
		
		ArrayList <String> sArrayL = new ArrayList();
		ArrayList <String> sArrayPL = new ArrayList();
		
		for(String iC : str){
			StringBuffer strB = new StringBuffer(iC);
			StringBuffer strRev = strB.reverse();
			
			if(Arrays.equals(iC.toCharArray(), strRev.toString().toCharArray())){
				sArrayPL.add(iC);
			}else{
				sArrayL.add(iC);
			}
		}
		
		System.out.println("========List of Array==========");
		for(String iS : sArrayL){
			System.out.print(" " +iS);
		}
		
		System.out.println();
		System.out.println("========List of Palindrome Array==========");
		for(String iSP : sArrayPL){
			System.out.print(" " +iSP);
		}

	}
}


