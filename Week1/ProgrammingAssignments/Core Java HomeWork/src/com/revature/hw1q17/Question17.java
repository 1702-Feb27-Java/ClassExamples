package com.revature.hw1q17;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Question17 {
	private static Scanner k;

	public static void main(String[] args) {
		
		int p = 0, r = 0, t = 0;
		k = new Scanner(System.in);
		
		System.out.println("Enter principal, rate, and time: ");
		
		try{
		p = k.nextInt();
		r = k.nextInt();
		t= k.nextInt();
		}catch(InputMismatchException e){
			System.out.println("those arent 3 numbers..");
		}
		
		System.out.println(Question17.calcInterest(p, r, t));
	}
	
	public static double calcInterest(double p, double r, double t){
		return p*r*t;
	}
}
