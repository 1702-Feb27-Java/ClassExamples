package com.revature.bankingapp.menu;

import java.util.Scanner;

import com.revature.bankingapp.Logging;
import com.revature.bankingapp.database.dao.Dao;
import com.revature.bankingapp.database.model.User;
import com.revature.bankingapp.database.service.Service;

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
			User c = Dao.getInstance().getUser(username);
			if (c != null){
				if (c.getPassword().equals(password)){
					Logging.info(String.format("Logging in %s : %s id:%d", c.getRole().getRole(), c.getUsername(), c.getUserId()));
					
					if(c.getRole().equals(Service.getInstance().getCustomerRole())){
						n = new CustomerMenu(c);
					} else if (c.getRole().equals(Service.getInstance().getAdminRole())
							|| c.getRole().equals(Service.getInstance().getEmployeeRole())){
						n = new EmployeeMenu(c);
					}
				}
			}
			
			//login failed
			if(n == null){
				Logging.warn("Failed login for username: " + username);
				System.out.println("Invalid Creditials");
			}
		}
		
		return n;
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
			
			if(Service.getInstance().getUser(username) != null){
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
		User customer = new User("firstname", "lastname", username, password, Service.getInstance().getCustomerRole());
		customer = Service.getInstance().saveAndReturnNewUser(customer);
		
		Logging.info("Created new User. " + customer.getUsername() + " id: " + customer.getUserId());
		
		//retrieves newly made user's menu
		return new CustomerMenu(customer);
	}
	
}
