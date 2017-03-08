package com.mory.RevatureBankingApp;

import java.util.Scanner;

public class BankApp {
	

	public static void main(String[] args) {
		introMenu();
		Scanner keyboard= new Scanner(System.in);
		int keyboardInput=Integer.parseInt(keyboard.nextLine());

	}
	
	
	public static void introMenu(){
		System.out.println("|-------------------------------------------|");
		System.out.println("|-----------Welcome to Mory's---------------|");
		System.out.println("|----------Awesome Banking App--------------|");
		System.out.println();
		System.out.println("1-)create an Account ");
		System.out.println("2-) To login");
		System.out.println("3-) Enter any other key to exit the application");
		System.out.print("Please choose an option:");
		
		switch(key)
	
	
	
	}
		
		public static void accountCreationMewnu(){
			System.out.println("Please choose a type of account to create.");
			System.out.println("Checking Account");
			System.out.println("Saving Account");
		}
		
		public static void loadMenu(int choice){
			switch(choice){
			case 1:
				accountCreationMenu(choice);
				break;
			case 2: 
				loginMenu();
				break;
			case 3:
				System.out.println("Thank you for using Mory's awesome banking app");
				System.exit(0);
			}
			
		}


		private static void loginMenu() {
			// TODO Auto-generated method stub
			
		}


		private static void accountCreationMenu(int lol) {
			// TODO Auto-generated method stub
			
		}
		
	
	
	}

