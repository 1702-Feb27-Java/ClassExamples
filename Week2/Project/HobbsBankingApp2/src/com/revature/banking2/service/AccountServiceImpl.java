package com.revature.banking2.service;

import java.util.ArrayList;

import com.revature.banking2.dao.AccountDao;
import com.revature.banking2.dao.AccountDaoImpl;
import com.revature.banking2.pojo.Account;
import com.revature.banking2.pojo.User;

public class AccountServiceImpl implements AccountService {
	
	private static AccountDao accountDao;
	private static AccountService accountService;
	
	private AccountServiceImpl() {
		accountDao = new AccountDaoImpl();
	}
	
	public void createAccount(User user, Account.Type type) {
		accountDao.addAccount(user, type);
	}
	
	public void updateAccount(Account account) {
		accountDao.updateAccount(account);
	}
	
	public static AccountService getAccountService() {
		if (accountService == null) {
			accountService = new AccountServiceImpl();
		}
		return accountService;
	}

	@Override
	public ArrayList<Account> getAccounts(User user) {
		return accountDao.getAccounts(user);
	}

}
