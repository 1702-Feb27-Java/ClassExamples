package q17;

import java.util.Scanner;

public class InterestCalc {

	public static void main(String[] args) {
		//Variables needed
		int principal;
		int rate;
		int time;
		int interest;
		//sets up scanner to read from standerd input
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to the interest calculator. Enter Principal: ");
		//gets the user input
		principal =  new Integer(scan.nextLine()); 
		
		System.out.println("Enter Rate: ");
		//gets user input
		rate =  new Integer(scan.nextLine()); 
		
		System.out.println("Enter Time: ");
		//get user input
		time =  new Integer(scan.nextLine()); 
		
		
		// calcuates the interest from the user givin variables and tells the user
		interest = principal * rate * time;
		System.out.println("The interest is: " + interest);
		scan.close();
	}

}
