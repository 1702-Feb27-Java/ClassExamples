package com.revature.bankingapp.menu;

import java.util.Scanner;

import com.revature.bankingapp.Logging;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Database;

public class CreateAccountMenu  implements IMenu {

	private Customer customer;

	
	public CreateAccountMenu(Customer customer) {
		this.customer = customer;
	}

	@Override
	public IMenu openMenu(Scanner scan) {

		boolean hasInvalidAccountName = true;
		boolean hasInvalidAccountType = true;
		String accountName;
		String accountType;
		//gets and valids new account's name
		do{
			System.out.print("Please enter your new accounts name: ");
			accountName = scan.nextLine();
			hasInvalidAccountName = false;
			char[] charArray = accountName.toCharArray();
			for(int i = 0; i < charArray.length; ++i){
				if (!Character.isAlphabetic(charArray[i]) 
						&& charArray[i] != '_' 
						&& !(i == 0 || charArray[i] == ' ') ){
					hasInvalidAccountName = true;
				}
			}
			
			if(hasInvalidAccountName){
				System.out.println("Invalid Account Name, must only be letters or underscore");
			}
		}while(hasInvalidAccountName);
		
		//gets and validates account type;
		do{
			System.out.println("Please type in what kind of account you want (checking or saving): ");
			accountType = scan.nextLine().toLowerCase();
			if(accountType.equals("checking") || accountType.equals("saving")){
				hasInvalidAccountType = false;
			}
		}while(hasInvalidAccountType);
		
		//creates the account and adds to the database
		Account account =new Account(accountName, accountType, 0, false);
		Database.getDatabase().addAccount(account);
		//adds account to the customer
		customer.getAccountIds().add(account.getId());
		System.out.println("Thank you, your account is now created and is pending approving.");
		Logging.getLogger().info(String.format("Customer ID: %d Name: %s is creating new account with ID: %d", customer.getId(), customer.getUsername(), account.getId()));
		return new CustomerMenu(customer);
	
	}

}
