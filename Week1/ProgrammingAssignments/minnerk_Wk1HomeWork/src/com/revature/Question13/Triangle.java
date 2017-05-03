package com.revature.Question13;

public class Triangle {

	public static void main(String[] args) {

		int count = 0;
		//Loop to print out 0 and 1 respectively
		for (int i = 0; i <= 4;i++){
			//Checks for when to print a newline before 0
			if (count == 1 || count  == 3 || count  == 6){
				System.out.println("\n");
			}
			System.out.print(" 0 ");
			count++;
	
			
			
			//Checks for when to print a newline before 1
			if (count == 1 || count  == 3 || count  == 6){
				System.out.println("\n");
			}
			System.out.print(" 1 ");
			count++;
		}

	}

}
