package com.revature.samsel_q14;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/*
 * Program to demonstrate switch cases for the following functionality:
 * 1. Find the square root using Math class
 * 2. Display todays date.
 * 3. Split the string "I am learning Core Java" and store in String array
 */
public class SwitchCPrg {

	public static void main(String[] args) {
		
		System.out.println("========OPTIONS============");
		System.out.println("1. Find the square root");
		System.out.println("2. Display todays date");
		System.out.println("3. Split the String");
		System.out.println("===========================");
		System.out.println("Enter the options : ");
		Scanner sInput = new Scanner(System.in);
		String sIn=null;
		try{
			sIn= sInput.nextLine();
			//User input can be Numbers [1 - 3] as options for selection
		 	while(Pattern.matches("^[1-3]", sIn) ==false){
		 		System.out.println("Enter options [1-3] : ");
		 		sIn= sInput.nextLine();
		 	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		int iIn = Integer.parseInt(sIn);
		switch(iIn){
		case 1:	System.out.println("Enter a Number : ");
				Scanner sNum = new Scanner(System.in);
				String  sNumber = sInput.nextLine();
				double d = Double.parseDouble(sNumber);
				System.out.println("Square root is : "+Math.sqrt(d));
				break;
				
		case 2:	Date dDATE = new Date();
				DateFormat dFormat = new SimpleDateFormat("MM-dd-YYYY");
				System.out.println("Todays Date : "+dFormat.format(dDATE));
				break;
				
		case 3: String iStr = "I am learning core Java";
				ArrayList <String> sArray = new ArrayList(); 
				StringTokenizer strTokens = new StringTokenizer(iStr);
				int i=0;
				while(strTokens.hasMoreTokens()){
					sArray.add(strTokens.nextToken());
					i++;
				}
				System.out.println("String Array : "+sArray);

				break;
		default:System.out.println("Default");
		}
	}
}
