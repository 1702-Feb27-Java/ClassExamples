package com.revature.bankingapp.menu;

import java.util.Scanner;

import com.revature.bankingapp.Logging;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Database;
import com.revature.bankingapp.model.Employee;

public class LoginMenu implements IMenu {

	/**
	 * creates a menu to Login Customer and Employee accounts and to create Customer accounts.
	 */
	public LoginMenu(){
		
	}
	
	@Override
	public IMenu openMenu(Scanner scan) {
		System.out.println("Welcome to the National Bank Of Revature");
		System.out.println("1: Login to Existing Account");
		System.out.println("2: Register for a new Account");
		System.out.println("q: Exit");
		

		boolean isInvalidOption = true;
		IMenu menu = null;
		do{
			System.out.print("Please select you option: ");
			String input = scan.nextLine();
			
			if(input == null){
				System.out.println("Invalid option.");
			}
			else if(input.equals("1")){
				menu = loginView(scan);
				isInvalidOption = false;
			} else if(input.equals("2")){
				menu = registerView(scan);
				isInvalidOption = false;
			} else if(input.equals("q")){
				menu = null;
				isInvalidOption = false;
			} else {
				System.out.println("Invalid option.");
			}
		}while(isInvalidOption);
		
		return menu;
		
	}
	
	private IMenu loginView(Scanner scan){
		IMenu n = null;
		System.out.println("Please Insert Username and Password. (No username to exit out screen)");
		
		while(n == null){
			String username;
			String password;
			
			// Username Prompt
			System.out.print("Username: ");
			username = scan.nextLine();
			if (username.isEmpty()){
				return this;
			}
			
			//Password Prompt
			System.out.print("Password: ");
			password = scan.nextLine();
			
			//determines if this is a customer, employee, or bad username
			//then check if password is correct, if so return UserAcccount
			Customer c = Database.getDatabase().getCustomerByUsername(username);
			if (c != null){
				if (c.getPassword().equals(password)){
					Logging.getLogger().info("Logging in Customer: " + c.getUsername() + " id: " + c.getId());
					return new CustomerMenu(c);
				}
			}			
			Employee e = Database.getDatabase().getEmployeeByUsername(username);
			if(e != null){
				if (e.getPassword().equals(password)){
					Logging.getLogger().info("Logging in Employee: " + e.getUsername() + " id: " + e.getId());
					return new EmployeeMenu(e);
				}
			}
			
			//login failed
			Logging.getLogger().warn("Failed login for username: " + username);
			System.out.println("Invalid Creditials");
		}
		
		return this;
	}
	
	private IMenu registerView(Scanner scan){
		boolean isValidUserName = false;
		boolean isValidPassword = false;
		String username = null;
		String password = null;
		
		//prompt and validates for username
		do{
			System.out.print("Please choose a username: ");
			username = scan.nextLine();
			
			if(Database.getDatabase().containsUsername(username)){
				System.out.println("Username already taken");
			} else {
				
				//checks to see if username.length is greater then 3 and has only "a-zA-z" and _ characters
				isValidUserName = username.length() >= 3;
				for (char c: username.toCharArray() ){
					if (!Character.isAlphabetic(c) && c != '_'){
						isValidUserName = false;
					}
				}
				
				
				
				// the username has valid characters and is unused, username is valid to be used.
				if (!isValidUserName){
					System.out.println("Invalid characters in Username");
				}
			}
		}while(!isValidUserName);
		
		
		do{
			System.out.print("Please choose a Password: ");
			password = scan.nextLine();
			

			boolean hasValidCharacters = password.length() > 5;
			for (char c: password.toCharArray()){
				
				//validates that password is over 5 in length and has  valid characters (1-9a-zA-Z, whitespace, _)
				if (!Character.isAlphabetic(c) && !Character.isDigit(c) && c != '_' &&  c != ' '){
					hasValidCharacters = false;
				}
			}
			// asks user to retype the password, if it matches, its valid
			if (hasValidCharacters){
				System.out.print("Please reenter your password: ");
				String password2 = scan.nextLine();
				isValidPassword = (password.equals(password2));
				if (!isValidPassword){
					System.out.println("Password entries do not match");
				}
			} else {
				System.out.println("Password has invalid characters");
			}
		}while(!isValidPassword);
		
		//creates an entry to be added to the Database
		Customer customer = new Customer(username, password);
		Database.getDatabase().addCustomer(customer);
		customer = Database.getDatabase().getCustomerByUsername(username);
		
		Logging.getLogger().info("Created new User. " + customer.getUsername() + " id: " + customer.getId());
		
		//retrieves newly made entry
		customer = Database.getDatabase().getCustomerByUsername(username);
		return new CustomerMenu(customer);
	}
	
}
