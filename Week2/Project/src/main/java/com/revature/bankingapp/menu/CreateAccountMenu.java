package com.revature.bankingapp.menu;

import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.revature.bankingapp.Logging;
import com.revature.bankingapp.database.dao.Dao;
import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.AccountType;
import com.revature.bankingapp.database.model.User;
import com.revature.bankingapp.database.service.Service;

public class CreateAccountMenu  implements IMenu {

	private User user;

	
	public CreateAccountMenu(User user) {
		this.user = user;
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
		
		//gets AccountType
		AccountType type = null;
		for (Integer id: Service.getInstance().getAccountTypes().keySet()){
			if (Service.getInstance().getAccountTypes().get(id).getType().equalsIgnoreCase(accountType)){
				type = Service.getInstance().getAccountTypes().get(id);
				break;
			}
		}
		
		//creates the account and adds to the database attached to the user
		Account account = new Account(accountType, type);
		Service.getInstance().createNewAccount(user, account);
		
		System.out.println("Thank you, your account is now created and is pending approving.");
		Logging.info(String.format("Customer ID: %d Name: %s is creating new account with name: %s", user.getUserId(), user.getUsername(), account.getAccountName()), user.getUserId());
		return new CustomerMenu(user);
	}

}
