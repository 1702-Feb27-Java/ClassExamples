package Q17;

import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		double principal, rate, time, interest;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter your Principal: ");
		principal = scan.nextDouble();
		System.out.println("\nPlease enter your Rate: ");
		rate = scan.nextDouble();
		System.out.println("\nPlease enter your Time: ");
		time = scan.nextDouble();
		
		interest = principal*rate*time;
		System.out.println("\nYour Interest will be: " + interest);
	}
}
