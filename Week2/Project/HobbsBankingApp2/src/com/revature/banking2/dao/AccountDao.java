package com.revature.banking2.dao;

import java.util.ArrayList;

import com.revature.banking2.pojo.Account;
import com.revature.banking2.pojo.User;

public interface AccountDao {
	
	void addAccount(User user, Account.Type type);

	void updateAccount(Account account);
	
	ArrayList<Account> getAccounts(User user);
	
}
