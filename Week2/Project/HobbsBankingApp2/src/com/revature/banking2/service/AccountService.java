package com.revature.banking2.service;

import java.util.ArrayList;

import com.revature.banking2.pojo.Account;
import com.revature.banking2.pojo.User;

public interface AccountService {
	
	void createAccount(User user, Account.Type type);
	
	void updateAccount(Account account);

	ArrayList<Account> getAccounts(User user);
	
}
