package com.revature;

import java.util.Scanner;

import com.revature.dao.CustomerDao;
import com.revature.dao.EmployeeDAO;

public class Main {

	static Scanner reader= new Scanner(System.in);
      public static void main(String[] args) {
    	  System.out.println("|++++++++++++++++++++++++++++++++++++++++|");
    	  System.out.println("Welcome to Mory's Banking Application");
    	  System.out.println("|+++++++++++++++++++++++++++++++++++++++++|");
    	  MainMenu();
      }
      
      public static void MainMenu(){
    	  System.out.println(" Enter 1: To create a new Account");
    	  System.out.println(" Enter 2: To login");
    	  System.out.println("Enter any anything else to quit to the application");
    	  System.out.print(" Please make a choice:");
    	  int choice= Integer.parseInt(reader.nextLine());
    	  switch(choice){
    	  case 1: 
    		  // account creation Menu
    		  customerSignUp();
    		  break;
    	  case 2: 
    		  // login menu
    		  loginMenu();
    		  break;
    	  default:
    		  System.out.println("Thank you for using our bank");
    		  System.exit(0);
    		  break;
    	  }
      }
      
      private static void loginMenu() {
    	  System.out.println("Enter 1 to login as a Customer");
    	  System.out.println("Enter 2 to login as an Employee");
    	  System.out.println("Enter 3 to login as an admin");
    	  System.out.println("Enter any other number to exit.");
    	  System.out.println("Please choose an option");
    	  
    	  int choice= Integer.parseInt(reader.nextLine());
    	  switch(choice){
    	  case 1: 
    		  // account creation Menu
    		  customerLoginMenu();
    		  break;
    	  case 2: 
    		  // Employee login
    		  EmployeeloginMenu();
    		  break;
    	  case 3:
    		  // admin login
    		/*  adminLoginMenu();*/
    		  break;
    	  default:
    		  System.out.println("Thank you for using our bank");
    		  System.exit(0);
    		  break;
    	  
    	  }
		
	}


	private static void EmployeeloginMenu() {
		System.out.print("Enter your username: ");
		 String username=reader.nextLine();
		 System.out.print("Enter your password: ");
		 String password=reader.nextLine();
		 EmployeeDAO empDao= new EmployeeDAO();
		
		  // this returns the user's id
		  
	  		int userid= empDao.EmployeeLogin(username, password);
	  		empDao.seeAllCustomersAccounts();
	  		// show user's account
		
	}

	private static void customerLoginMenu() {
		System.out.print("Enter your username: ");
		 String username=reader.nextLine();
		 System.out.print("Enter your password: ");
		 String password=reader.nextLine();
		  CustomerDao customerDao= new CustomerDao();
		
		  // this returns the user's id
		  
	  		int userid= customerDao.customerLogin(username, password);
	  		 customerDao.listOfAccounts(userid);
	  		// show user's account
	  		
	  		System.out.println(" What actions would you like to perform");
	  		System.out.println(" Enter 1 to deposit");
	  		System.out.println(" Enter 2 to withdraw");
	  		System.out.println(" Enter 3 to Open a new Account");
	  		System.out.println(" Enter any thing else to exit");
	  		 int choice= Integer.parseInt(reader.nextLine());
	  		 switch(choice){
	  		 case 1:
	  		 System.out.println("in which account would you like to depost");
	  		 	int accountNum= Integer.parseInt(reader.nextLine());
	  			 System.out.print("How much would you like to deposit?");
	  			 double amount= Double.parseDouble(reader.nextLine());
	  			 customerDao.deposit(accountNum, amount);
	  			 break;
	  		 case 2:
	  			 System.out.println("in which account would you like to withdraw");
		  		 	int accountNum2= Integer.parseInt(reader.nextLine());
		  			 System.out.print("How much would you like to withdraw?");
		  			 double amount2= Double.parseDouble(reader.nextLine());
		  			 customerDao.withdraw(accountNum2, amount2);
	  			 break;
	  		 case 3:
	  			 System.out.println("Please choose an account type: 1 for checking and 2 for savins");
				  choice= Integer.parseInt(reader.nextLine());
				  CustomerDao customerdao= new CustomerDao();
				  customerdao.openAnAccount(userid,choice);
	  			 
	  		 default:
	  			 System.exit(0);
	  		 }
		 
	}

	
	public static void customerSignUp(){
    	  System.out.print("Enter your first Name: ");
    	  String firstName=reader.nextLine();
    	  System.out.print("Enter your last name: ");
    	  String lastName=reader.nextLine();
    	  System.out.print("Enter your username: ");
    	  String username=reader.nextLine();
    	  System.out.print("Enter your password: ");
    	  String password=reader.nextLine();
    	  CustomerDao customerDao= new CustomerDao();
  		 customerDao.addCustomer(firstName, lastName, username, password);
  		
      }

	
}


