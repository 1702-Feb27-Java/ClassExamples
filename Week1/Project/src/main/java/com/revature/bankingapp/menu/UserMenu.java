package com.revature.bankingapp.menu;

import java.util.List;
import java.util.Map;

import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Database;

public abstract class UserMenu implements IMenu{

	/**
	 * Prints out the list of Account and shows if they are still appending approval
	 * @param accountIds a list of accountIds to limit the list to, or null if to show all accounts
	 */
	protected void printAccounts(List<Integer> accountIds) {
		
		Map<Integer, Account> map = Database.getDatabase().getAccounts();
		for (Map.Entry<Integer, Account> pair: map.entrySet()){
			if(accountIds == null || accountIds.contains(pair.getKey()))
				System.out.println(String.format("%d: %s %s", pair.getKey().intValue(), pair.getValue().getAccountName(), pair.getValue().isApproved() ? "" : "-pending approval"));
		}
	}

}
