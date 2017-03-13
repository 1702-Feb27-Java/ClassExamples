package com.revature.bankingapp.menu;

import java.util.List;
import java.util.Scanner;

import com.revature.bankingapp.Logging;
import com.revature.bankingapp.database.dao.Dao;
import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.User;
import com.revature.bankingapp.database.service.Service;

public class CustomerMenu extends UserMenu {

	private User user;

	/**
	 * Creates a menu interface for the customer to operator
	 * @param c Customer object that represents the user
	 */
	public CustomerMenu(User c) {
		this.user = c;
	}

	@Override
	public IMenu openMenu(Scanner scan) {
		System.out.println("Hello " + user.getUsername());
		System.out.println("q: Logout");
		System.out.println("n: Apply for a new account");
		
		//lists the accounts the customer has
		List<Account> accounts = Service.getInstance().getAccountFromUser(user);
		this.printAccounts(accounts);
		
		IMenu menuToReturn = null;;
		boolean validOption = false;
		
		//gets the User input
		while (!validOption){
			System.out.print("Please select your option: ");
			String input = scan.nextLine();
			if (input.equals("q")){
				//user wants to logout, returning to LoginMenu
				menuToReturn = new LoginMenu();
				validOption = true;
			} else if (input.equals("n")){
				//apply for new account
				menuToReturn =  new CreateAccountMenu(user);
				validOption = true;
			} else {
				
				try {
					int n = Integer.parseInt(input);
					
					//checks to see if the customer has access to that account.
					boolean controlsAccount = false;
					for(Account account: accounts){
						if(account.getAccountId().equals(n)){
							controlsAccount = true;
							break;
						}
					}
					
					if(controlsAccount){
						//customer must have an account id on its list of account ids
						Account account = Service.getInstance().getAccount(n);
						if (account != null){
							menuToReturn = new AccountMenu(this.user, account);
							validOption = true;
						} else {
							Logging.error(String.format("Customer tried to access account #%d but it didn't exist", n));
						}
					}
				} catch (NumberFormatException e) {
					//was not q, n, or a number
					
				} catch (IndexOutOfBoundsException e){
					//user selected an non existed account option
				} 
			}
				
			if(!validOption){
				System.out.println("Invalid Option Selected.");
			}
			
		}
		
		
		return menuToReturn;
		
	}

}
