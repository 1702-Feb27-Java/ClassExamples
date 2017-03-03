package com.revature.Q14;

import java.math.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class Q14 {

	public static void main(String[] args) {
		int val = 1;
		int numberOfCases = 4;
		String s = "I am learning Core Java"; // string used for switch case
		for (int i = val; val < numberOfCases; val++) {
			switch (val) {
			case 1:
				System.out.println(findSquareRoot(25)); // should output 5
				break;
			case 2:
				System.out.println(getDate()); //get the date
				break;
			case 3:
				System.out.println(splitString(s)); //split the string
				break;
			default:
				//print default statement
				System.out.println("No case argument.");
			}//switch
		}//for
	}// main

	public static double findSquareRoot(double n) { //compute the square root
		return Math.sqrt(n);
	}// findSquareRoot()

	public static String getDate() { // get the date
		SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateForm.format(date);
	}// getDate()

	public static String splitString(String str) { // method to split the string
		ArrayList<String> stringList = new ArrayList<String>();
		StringTokenizer s = new StringTokenizer(str);
		while (s.hasMoreTokens()) {
			stringList.add(s.nextToken());
		}
		for(int i=0; i<stringList.size();i++){
			System.out.println(stringList.get(i));
		}
		return "";
	}// splitString()
}// Q14
