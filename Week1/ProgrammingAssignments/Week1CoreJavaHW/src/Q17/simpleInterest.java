package Q17;
//java.util.scanner is being imported so i can create a scanner object to call 
//the calculate method i created
import java.util.Scanner;

public class simpleInterest {
		
	// static float principal = 0 + (float) (Math.random() * 100000);
		//used this to test the calculate method ^^^^^^^^^^^
	public static void main(String[] args) {

		//using Scanner sc to request user input
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter your current balance");
		float userPrinc = sc.nextFloat();
		System.out.println(" Enter your current interest rate");
		float userRate = sc.nextFloat();
		System.out.println("Enter a duration of time in years");
		float userTime = sc.nextInt();
		// sc.close();
		//i just used this to make sure the information was being displayed properly
			//System.out.println(userPrinc + " " + userRate + " " + userTime);
		
		//calling the calculate method i created and placing the data from the scanner in to it
		calculate(userPrinc, userRate, userTime);
		
		sc.close();

	}
	// i started by creating a calculator method to call after the Scanner
	public static void calculate(float userPrinc, float userRate, float userTime) {

		
				// 1 + (int) (Math.random() * 100);
				//System.out.println(timeInYears);
		float interest = (userPrinc * userRate * userTime);
			
				// System.out.println(principal);
				//System.out.println(interest);
		System.out.println("your interest will be " + interest);
	}

}
