package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.Account;
import com.revature.pojo.User;

public interface DAOCustomer {
	//firstname, lastname, username, password
	int addCustomer(String fn, String ln, String un, String pw);
	
	int applyForAccount(int userId, int actId, int typeId);
	
	ArrayList<String> loginCustomer(String un, String pw);
	
	ArrayList<Account> getAccounts(int userId);
	
	public String getAccountType(int typeId); 
	
	public String getStatus(int statusId);
	
	double getBalance(int accountId);
	
	double setBalance(int accountId, double balance);
	
	ArrayList<User> getAllUsers();
}
