package com.mory.question14;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Q14 {

	public static void main(String[] args) {
		System.out.println("---------------------------------------");
		System.out.println("Please enter: \n"
				+"1:Enter 1 to find  the square root of a number.\n"
				+"2:Enter 2 To display the current date. \n"+
				"3: Enter 3 To  display 'I  am learning java' in Array format.\n"
				+"4: Any other number:  to exit the console.");
		System.out.println("-----------------------------------------");
		Scanner inputReader = new Scanner(System.in);
		int choice = Integer.parseInt(inputReader.nextLine());
		displayCase(choice);
		inputReader.close();

	}
	
	public static void displayCase(int caseNum) {
		switch (caseNum) {
		case 1:
			System.out.println("Please enter the number of which you want to take the square root:");
			Scanner inputNum = new Scanner(System.in);
			int sqrtNum= Integer.parseInt(inputNum.nextLine());
			
			findSquareRoot(sqrtNum);
			break;
		case 2:
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			System.out.println("The current date is " +dateFormat.format(date)); 
			break;
			
		case 3: 
			String str="I am learing java";
			splitString( str);
			default:
				System.out.println("You have exited the console.");
				System.exit(0);
				
		

		}

	}
	
	// this methods find the square root of a number

	public static void findSquareRoot(int number) {
		System.out.println(Math.sqrt(number));

	}
	
	// this method split a string into an array
	public static void splitString(String str){
		String[] strArray= str.split(" ");
			System.out.println(Arrays.toString(strArray));
		
	}

}
