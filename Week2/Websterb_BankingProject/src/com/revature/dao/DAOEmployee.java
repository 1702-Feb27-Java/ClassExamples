package com.revature.dao;

import java.util.ArrayList;
import com.revature.pojo.Account;

public interface DAOEmployee {
	
	/**
	 * Tries to add employee to DB
	 * @param fn
	 * @param ln
	 * @param un
	 * @param pw
	 * @return
	 */
	int addEmployee(String fn, String ln, String un, String pw); 
	
	/**
	 * Tries to log in as employee
	 * @param username
	 * @param password
	 * @return
	 */
	ArrayList<String> loginEmployee(String un, String pw);
	
	/**
	 * Gets all accounts that are unapproved
	 * @return
	 */
	ArrayList<Account> getUnapprovedAccounts();
	
	/**
	 * Edit the status of an account to approved/denied
	 * @param actId
	 * @param statusId
	 * @return
	 */
	public boolean editAccountStatus(int actId, int status);
	
	/**
	 * set the resolver field for when you approve/decline account
	 * @param empId
	 * @param actId
	 * @return
	 */
	public boolean setResolverId(int empId, int actId);
	
	/**
	 * Get accounts based on your employee id
	 * @param empId
	 * @return
	 */
	ArrayList<Account> getAccounts(int empId);
	
	/**	 
	 * Login as admin
	 * @param un
	 * @param pw
	 * @return
	 */
	public boolean adminLogin(String un, String password);
	
}
