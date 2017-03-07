package com.revature.bankingapp.menu;

import java.util.Scanner;

import com.revature.bankingapp.Logging;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Database;
import com.revature.bankingapp.model.Employee;

public class EmployeeMenu extends UserMenu {

	private Employee employee;

	/**
	 * Creates a menu interface for the employee to operate.
	 * @param e Employee object that represents the user
	 */
	public EmployeeMenu(Employee e) {
		this.employee = e;
	}

	@Override
	public IMenu openMenu(Scanner scan) {
		showMenu();
		
		IMenu menuToReturn = null;
		boolean isInvalidOption = true;
		boolean canExit = false;
		do{
			isInvalidOption = true;
			String s = scan.nextLine();
			if (s.equals("l")){
				menuToReturn = this.showTransactions(scan);
				isInvalidOption = false;
				canExit = true;
			} else if (this.employee.isAdmin() && s.equals("a")){
				createNewEmployee(scan);
				isInvalidOption = false;
				showMenu();
			} else if (s.equals("q")){
				menuToReturn = new LoginMenu();
				isInvalidOption = false;
				canExit = true;
			}
			
			
			if(isInvalidOption){
				System.out.println("Invalid Option");
			}
			
		}while(!canExit);
		
		return menuToReturn;
		
	}

	private void showMenu() {
		System.out.println(String.format("Hello Employee %s", employee.getUsername()));
		System.out.println("l: List all accounts");
		
		if(employee.isAdmin()){
			System.out.println("a: Create a new Employee account.");
		}
		System.out.println("q: logout");
		System.out.print("Please select your option: ");
	}
	
	private IMenu showTransactions(Scanner scan){
		

		IMenu menuToReturn = null;
		boolean isInvalidOption = true; 
		printAccounts(null);
		System.out.println("q: Exit");
		System.out.print("Please select your option: ");
		
		do{
			String input = scan.nextLine();
			
			//go back to the beginning of this menu.
			if(input.equals("q")){
				return this;
			}
			
			try {
				int n = Integer.parseInt(input);
				Account account = Database.getDatabase().getAccount(n);
				if (account != null){
					menuToReturn = new AccountMenu(employee, account, employee.isAdmin(), true);
					isInvalidOption = false;
				}
			} catch (NumberFormatException e) {
				//was not number
				
			} catch (IndexOutOfBoundsException e){
				//user selected an non existed account option
			}
			if(isInvalidOption){
				System.out.println("Invalid Option");
			}
		} while (isInvalidOption);
		
		return menuToReturn;
	}

	private void createNewEmployee(Scanner scan){
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
				if (!Character.isAlphabetic(c) && !Character.isDigit(c) && c != '_' && c != ' '){
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
		Employee employee = new Employee(username, password, false);
		Database.getDatabase().addEmployee(employee);
		employee = Database.getDatabase().getEmployeeByUsername(username);
		
		Logging.getLogger().info("Created new Employee. " + employee.getUsername() + " id: " + employee.getId());
		
		
	}
	

}
