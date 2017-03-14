package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.AccountClass;
import com.revature.pojo.UserClass;

// interface for DAO methods used in reading and updating the Accounts table in the database
public interface DAOAccount {
	
	// add
	public void addAccount(int accountType, UserClass uc);
	
	// update 
	public void updateBalance(AccountClass ac, double balance);
	public void updateStatus(AccountClass ac, int status, int resolver);
	
	// read
	ArrayList<AccountClass> getAllAccounts();
	ArrayList<AccountClass> getAccountsByUserID(int id);
	ArrayList<AccountClass> getAccountsByStatus(int status);
	AccountClass getAccountByID(int accountID);  // used for balance update
}
