package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.AccountClass;
import com.revature.pojo.UserClass;

public interface DAOAccount {
	
	// add
	int addAccount(int accountID, int typeID);
	
	// update 
	int updateBalance(AccountClass ac, double balance);
	int updateStatus(AccountClass ac, int status);
	
	// read
	ArrayList<AccountClass> getAllAccounts();
	ArrayList<AccountClass> getAccountsByUserID(int id);

}
