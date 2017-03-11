package com.revature.bankingapp.menu;

import java.util.List;
import java.util.Scanner;

import com.revature.bankingapp.Logging;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Database;

public class CustomerMenu extends UserMenu {

	private Customer customer;

	/**
	 * Creates a menu interface for the customer to operator
	 * @param c Customer object that represents the user
	 */
	public CustomerMenu( Customer c) {
		this.customer = c;
	}

	@Override
	public IMenu openMenu(Scanner scan) {
		System.out.println("Hello " + customer.getUsername());
		System.out.println("q: Logout");
		System.out.println("n: Apply for a new account");
		
		//lists the accounts the customer has
		List<Integer> accountIds = customer.getAccountIds();
		this.printAccounts(accountIds);
		
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
				menuToReturn =  new CreateAccountMenu(customer);
				validOption = true;
			} else {
				
				try {
					int n = Integer.parseInt(input);
					
					if(customer.getAccountIds().contains(n)){
						//customer must have an account id on its list of account ids
						Account account = Database.getDatabase().getAccount(n);
						if (account != null){
							menuToReturn = account.isApproved() ?
									new AccountMenu(this.customer, account, true) :
									new AccountMenu(this.customer, account);
							validOption = true;
						} else {
							Logging.getLogger().error(String.format("Customer tried to access account #%d but it didn't exist", n));
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
