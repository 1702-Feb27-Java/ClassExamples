package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.AccountClass;
import com.revature.pojo.UserClass;

public interface DAOAccount {
	
	// add
	public void addAccount(AccountClass ac, int customerID);
	
	// update 
	public void updateBalance(AccountClass ac, double balance);
	public void updateStatus(AccountClass ac, int status);
	
	// read
	ArrayList<AccountClass> getAllAccounts();
	ArrayList<AccountClass> getAccountsByUserID(int id);
	AccountClass getAccountByID(int accountID);  // used for balance update
}
