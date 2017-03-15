package com.revature;

import java.util.Scanner;

import com.revature.dao.AdminDao;
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
    	  System.out.println(" Enter 1: To sign up as a customer.");
    	  System.out.println(" Enter 2: To login.");
    	  System.out.println("Enter any anything else to quit to the application.");
    	  System.out.print(" Please make a choice: ");
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
    		  System.out.println("Thank you for using our bank!");
    		  System.exit(0);
    		  break;
    	  }
      }
      
      private static void loginMenu() {
    	  System.out.println("Enter 1 to login as a Customer.");
    	  System.out.println("Enter 2 to login as an Employee.");
    	  System.out.println("Enter 3 to login as an admin.");
    	  System.out.println("Enter any other number to exit.");
    	  System.out.print("Please choose an option: ");
    	  
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
    		  
    		adminLoginMenu();
    		  break;
    	  default:
    		  System.out.println("Thank you for using our bank!");
    		  System.exit(0);
    		  break;
    	  
    	  }
		
	}


	private static void adminLoginMenu() {
		System.out.print("Enter your username: ");
		 String username=reader.nextLine();
		 System.out.print("Enter your password: ");
		 String password=reader.nextLine();
		 AdminDao admindao= new AdminDao();
		 if(admindao.getRoleid(admindao.adminLogin(username, password))==1){
			 System.out.println("Enter 1 to add an employee. ");
			 System.out.println(" Enter anything else to exit the application. ");
			 System.out.print("Please enter a choice: ");
			 int choice=Integer.parseInt(reader.nextLine());
			 switch(choice){
			 case 1:
				 System.out.print("Enter your first Name: ");
		    	  String firstName3=reader.nextLine();
		    	  System.out.print("Enter your last name: ");
		    	  String lastName3=reader.nextLine();
		    	  System.out.print("Enter your username: ");
		    	  String username3=reader.nextLine();
		    	  System.out.print("Enter your password: ");
		    	  String password3=reader.nextLine();
		    	  AdminDao admin= new AdminDao();
		    	  admin.addEmployee(firstName3, lastName3, username3, password3);
		    	  System.out.println("The employee:" + firstName3 +  lastName3 +" with username:"+ username3 + " and password:"+password3+ " has been added ");
		    	  
		    	  break;
			default:
				System.out.println("Thank you for loggin in!");
				System.exit(0);
			 }
			 System.out.println(" You do not access to this level. Please return!");
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
	  		int roleid=empDao.getRoleid(userid);
	  		if(roleid==2){
	  		empDao.seeAllCustomersAccounts();
	  		}
	  		else{
	  			System.out.println(" You do not have the access to this menu.");
	  		}
	  		System.out.println(" which account would you like to approve? please enter the account's number");
	  		 int accoutNumber=Integer.parseInt(reader.nextLine());
	  		empDao.approveAccount(accoutNumber);
	  		System.out.println("The account number "+ accoutNumber+ " has been approved.");
		
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
	  		
	  		System.out.println(" Which action would you like to perform ?");
	  		System.out.println(" Enter 1 to deposit.");
	  		System.out.println(" Enter 2 to withdraw.");
	  		System.out.println(" Enter 3 to Open a new Account.");
	  		System.out.println(" Enter any thing else to exit.");
	  		 int choice= Integer.parseInt(reader.nextLine());
	  		 switch(choice){
	  		 case 1:
	  		 System.out.print("in which account would you like to depost? ");
	  		 	int accountNum= Integer.parseInt(reader.nextLine());
	  			 System.out.print("How much would you like to deposit? ");
	  			 double amount= Double.parseDouble(reader.nextLine());
	  			 customerDao.deposit(accountNum, amount);
	  			 break;
	  		 case 2:
	  			 System.out.print("in which account would you like to withdraw?");
		  		 	int accountNum2= Integer.parseInt(reader.nextLine());
		  			 System.out.print("How much would you like to withdraw? ");
		  			 double amount2= Double.parseDouble(reader.nextLine());
		  			 customerDao.withdraw(accountNum2, amount2);
	  			 break;
	  		 case 3:
	  			 System.out.println("Please choose an account type: 1 for checking and 2 for savings");
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
  		 System.out.println(" You have registered with us. Thank you!");
  		
      }

	
}


