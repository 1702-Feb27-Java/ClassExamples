package com.revature.banking2.service;

import java.util.ArrayList;

import com.revature.banking2.pojo.Account;
import com.revature.banking2.pojo.User;

public interface AccountService {
	
	void updateAccount(Account account);

	ArrayList<Account> getAccounts(User user);
	
}
