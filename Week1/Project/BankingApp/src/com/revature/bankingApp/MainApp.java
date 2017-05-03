package com.revature.bankingApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to simulate the driver application of a Banking app
 * Assistance provide by David, Marco, Marcus and Danni
 * @author Nick
 *
 */

public class MainApp {

	//static variables to be used throughout the program
	static Customer new_person = new Customer();
	
	static ArrayList<Customer> Cust_list = new ArrayList<Customer>();
	
	public static void main(String[] args) {
		ReadFromCustFile();
		Menu();
	}
	
	/**
	 * Function to initialize the menu for application
	 */
	static void Menu() {
		
		Scanner keyboard = new Scanner(System.in);
		int input;
		
		do {
	
			System.out.println("Welcome to Revature Bank, Please select one of the following number choices below:");
			System.out.println("1: Sign Up");
			System.out.println("2: Log in");
			System.out.println("0: Exit");
		
			System.out.print("Enter your Selection: ");
			input = keyboard.nextInt();
			if ( input == 1)
				SignUp(input);
			else if ( input == 2)
				LogIn(input); 
			else
				System.out.println("Thank you for Banking with us. Good Bye!");
		} while( input != 0);	
	}
	
	/**
	 * Method for starting the sign up "sub-menu"
	 * @param menu_num number for menu selection
	 */
	static void SignUp(int menu_num) {
				 
		if( menu_num == 1) {
			
			System.out.println();
					 
			System.out.print("Please enter a user name: ");
			Scanner keyboard1 = new Scanner(System.in);
			String name = keyboard1.nextLine();
			new_person.setUser_name(name);
					
			System.out.print("Please enter a password: ");
			String pass = keyboard1.nextLine();
			new_person.setPassword(pass);
					 
			System.out.println("You will receive both a Checking and a Savings account");
			new_person.setAccountName1("Checking");
			new_person.setAccountName2("Savings");
			System.out.println();
			Cust_list.add(new_person);
			//System.out.println(Cust_list.size());
			WriteToCustFile(Cust_list);
			
		}
		else {
			System.out.println("Not the right parameter!");
		}
	} 
	
	
	/**
	 * Function to call the log in of the user. User being customer, employee
	 * @param menu_num to passed in by user input indicating to log in.
	 */
	static void LogIn(int menu_num) {
		//will start with the customer first, then work for the employee, and admin
		
		
		if ( menu_num == 2 ) {
			System.out.println();
			System.out.println("Are you a Customer, Employee or Admin? Select the appropiate option below");
			System.out.println("1: Customer");
			System.out.println("2: Employee");
			System.out.println("3: Admin");
			System.out.print("Enter your choice: ");
			
			Scanner keyboard = new Scanner(System.in);
			int this_num = keyboard.nextInt();
			switch ( this_num ) {
				case 1: CustomerMenu();
				break;
				
				//case 3: 
				
			}
		}
	}
	
	public static void CustomerMenu() {
		Scanner reader = new Scanner(System.in);
		String cust_user = "";
		String cust_pass = "";
		
		boolean is_loggedIn = false;
		
		System.out.print("Enter your user name: ");
		cust_user = reader.next();
		System.out.print("Enter your password: ");
		cust_pass = reader.next();
		
		//function to check file for credentials
		//then put it into a local ArrayList of customers
		Scanner cust_input = new Scanner(System.in);
		int cust_num = 0;
		do {
			for( int i = 0; i < Cust_list.size(); i++ ) {
				//System.out.println(":"+Cust_list.get(i).getUser_name()+":");
				if( Cust_list.get(i).getUser_name().equals(cust_user) & Cust_list.get(i).getPassword().equals(cust_pass)) {
					is_loggedIn = true;
					System.out.println("Welcome, What do you want to do?");
					System.out.println();
					System.out.println("1: Widthdraw");
					System.out.println("2: Deposit");
					System.out.println("0: Exit");
					System.out.print("Enter your choice: ");
					cust_num = cust_input.nextInt();
					System.out.println();
					
					if ( cust_num == 1 )
						Widthdraw(Cust_list.get(i));
					else if ( cust_num == 2)
						Deposit(Cust_list.get(i));
					
				}
				
			}
				//else ( !(Cust_list.get(i).getUser_name().equals(cust_user)) & !(Cust_list.get(i).getPassword().equals(cust_pass)) ) {
			if (!is_loggedIn)
			{
					System.out.println("Invalid credentials! Please log in again");
					System.out.println();
				//break;
			}
		}  while ( cust_num != 0);
		
			
	}
	/**
	 * Function for the customer to withdraw from their accounts
	 * @param obj of type customer to be passed in.
	 */
	static void Widthdraw(Customer obj) {
		System.out.println("Which account would you like to widthdraw from?");
		System.out.println("1: Checking");
		System.out.println("2: Savings");
		System.out.print("Enter your choice: ");
	
		Scanner width_input = new Scanner(System.in);
		int width_num = width_input.nextInt();
		Scanner read_num = new Scanner(System.in);
		
		
		if ( width_num == 1 ) {
			System.out.print("How much do you want to widthdraw?: ");
			int num = read_num.nextInt();
			
			if ( obj.getBalance1() - num <= 0 ) {
				System.out.println("You can not widthdraw. Here is your balance: " + obj.getBalance1());
				System.out.println("Deposit money first!");
			}
			else {
				obj.setBalance1(obj.getBalance1() - num);
				//System.out.println(obj.getBalance1());
			}
		}
		else {
			System.out.print("How much do you want to widthdraw?: ");
			int num = read_num.nextInt();
			
			if ( obj.getBalance2() - num <= 0 ) {
				System.out.println("You can not widthdraw. Here is your balance: " + obj.getBalance2());
				System.out.println("Deposit money first!");
			}
			else {
				obj.setBalance2(obj.getBalance2() - num);
				//System.out.println(obj.getBalance1());
			}
			
			
		}
		//place object back to customer list or at least sort through it
		for( int i = 0; i < Cust_list.size(); i++ ) {
			if ( Cust_list.get(i).getUser_name().equals(obj.getUser_name()) ) {
				Cust_list.set(i, obj);
			}
		}
		WriteToCustFile(Cust_list);
	}
	/**
	 * Function to deposit to customers account of their choosing
	 * @param obj is of type Customer
	 */
	static void Deposit(Customer obj) {
		System.out.println("Which account would you like to deposit to?");
		System.out.println("1: Checking");
		System.out.println("2: Savings");
		System.out.print("Enter your choice: ");
	
		Scanner depo_input = new Scanner(System.in);
		int depo_num = depo_input.nextInt();
		Scanner read_num = new Scanner(System.in);
		
		
		if ( depo_num == 1 ) {
			System.out.print("How much do you want to deposit?: ");
			int num = read_num.nextInt();
			
			obj.setBalance1(obj.getBalance1() + num);
			//System.out.println(obj.getBalance1());
			
		}
		else {
			System.out.print("How much do you want to deposit?: ");
			int num = read_num.nextInt();
			
			
			obj.setBalance2(obj.getBalance2() + num);
			//System.out.println(obj.getBalance1());
			
		}
		//place object back to customer list or at least sort through it
		for( int i = 0; i < Cust_list.size(); i++ ) {
			if ( Cust_list.get(i).getUser_name().equals(obj.getUser_name()) ) {
				Cust_list.set(i, obj);
			}
		}
		WriteToCustFile(Cust_list);
	}
	
	/**
	 * Method to initialize menu for the Administrator
	 */
	public static void AdminMenu() {
		
		System.out.println("What do you want to do?");
		System.out.println("1: View Accounts");
		System.out.println("2: Edit Accounts");
		System.out.print("Enter your choice: ");
		Scanner admin_input = new Scanner(System.in);
		int admin_num = admin_input.nextInt();
		
		
	}
	
	/**
	 * Function to write to the customers "database"
	 * @param obj of type Customer
	 */
	static void WriteToCustFile(ArrayList<Customer> obj) {
		File file = new File("Customers.txt");
		FileWriter same_file = null;
		BufferedWriter BW = null;
		try {
			same_file = new FileWriter(file);
			BW = new BufferedWriter(same_file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			
			for ( int i = 0; i < obj.size(); i ++ ) {
				BW.write(obj.get(i).getUser_name() + ":" + obj.get(i).getPassword() + ":" 
						+ obj.get(i).getAccountName1() + ":" + obj.get(i).getBalance1() + ":" + obj.get(i).getAccountName2() + ":" + obj.get(i).getBalance2());
				BW.write("\n");
				
			}
			
			
		} catch (IOException e) {
			System.out.println("Not the correct parameters to write to the file!!");
			e.printStackTrace();
		} finally {
			try {
				BW.close();
			} catch (IOException e) {
				System.out.println("Could not close the file!!!");
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Reading from customer file and putting it into the ArrayList of customers before the program starts
	 * so I can check the data of the customers
	 */
	static void ReadFromCustFile() {
		File file = new File("Customers.txt");
		FileReader same_file = null;
		BufferedReader BR = null;
		
		try {
			same_file = new FileReader(file);
			BR = new BufferedReader(same_file);
		} catch (IOException e) {
			System.out.println("The file is not located here. It may have been deleted or moved!");
			e.printStackTrace();
		}
		
		try{
			
			String line = null;

			while ((line = BR.readLine()) != null) {
				String[] temp = line.split(":");
				Cust_list.add(new Customer(temp[0], temp[1], temp[2], Double.parseDouble(temp[3]), temp[4], Double.parseDouble(temp[5] )));
							//String user_name, String password, String AccountName1, double Balance1, String AccountName2, double Balance2
			}
				
			
		} catch (IOException e) {
			System.out.println("else nothing to read");
		}
	}
}
