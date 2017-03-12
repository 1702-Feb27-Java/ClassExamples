package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.Account;
import com.revature.pojo.User;

public interface DAOCustomer {

	/**
	 * Try to add customer to DB
	 * @param fn
	 * @param ln
	 * @param un
	 * @param pw
	 * @return
	 */
	int addCustomer(String fn, String ln, String un, String pw);
	
	/**
	 * Gets all users in DB
	 */
	ArrayList<User> getAllUsers();
	
	/**
	 * Add account to DB with pending status
	 * @param userId
	 * @param actId
	 * @param typeId
	 * @return
	 */
	int applyForAccount(int userId, int actId, int typeId);
	
	/**
	 * Try to Login as customer
	 */
	ArrayList<String> loginCustomer(String un, String pw);
	
	/**
	 * Admin login as customer
	 */
	int loginCustomer(String un);
	
	/**
	 * Get all accounts of user
	 */
	ArrayList<Account> getAccounts(int userId);
	
	/**
	 * Uses lookup table to get accountType String
	 * @param typeId
	 * @return
	 */
	public String getAccountType(int typeId); 
	
	/**
	 * Uses lookup table to get status String
	 */
	public String getStatus(int statusId);
	
	/**
	 * Gets balance for account 
	 * @param accountId
	 * @return
	 */
	double getBalance(int accountId);
	
	/**
	 * Sets balance for account
	 */
	double setBalance(int accountId, double balance);
	

}
