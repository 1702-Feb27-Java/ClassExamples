package com.week1homework.question17;
import java.util.Scanner;

public class InterestCalc
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		System.out.println("------Simple Interest Calculator------\n");
		
		System.out.println("Please Insert a Principal amount: ");
		int principal = scan.nextInt();
		
		System.out.println("Please Insert a Rate: ");
		int rate = scan.nextInt();
		
		System.out.println("Please Insert an amount of time in Years: ");
		int time = scan.nextInt();
		
		System.out.println("Your simple interest is: " + (principal * rate * time));
		

	}

}
