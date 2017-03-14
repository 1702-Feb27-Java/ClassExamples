package com.revature.bankingapp.menu;

import java.util.List;
import java.util.Map;

import com.revature.bankingapp.database.dao.Dao;
import com.revature.bankingapp.database.model.Account;

public abstract class UserMenu implements IMenu{

	/**
	 * Prints out the list of Account and shows if they are still appending approval
	 * @param accountIds a list of accountIds to limit the list to, or null if to show all accounts
	 */
	protected void printAccounts(List<Account> accounts) {
		
		for (Account account: accounts){
			System.out.println(String.format("%d: %s %s", account.getAccountId(), account.getAccountName(), account.getStatus().getStatus()));
		}
	}

}
