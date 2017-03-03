package com.mory.question17;

import java.util.Scanner;

public class Q17 {

	public static void main(String[] args) {
		Scanner reader= new Scanner(System.in);
		System.out.print("Enter the pricipal amount:");
		double principal= Double.parseDouble(reader.nextLine());
		System.out.print("Enter the rate");
		double rate= Double.parseDouble(reader.nextLine());
		System.out.print("Enter the time");
		int time=Integer.parseInt(reader.nextLine());
		System.out.print("The simple Interest is :"+ calcSimpleInterest(principal,rate,time));
		reader.close();
	}
	/***
	 * 
	 * @param principal the principal method.
	 * @param rate the rate of interest.
	 * @param time the number of year.
	 * @return Simple interest.
	 */
	public static double calcSimpleInterest(double principal, double rate, int time){
		return principal*rate*time;
	}
}
