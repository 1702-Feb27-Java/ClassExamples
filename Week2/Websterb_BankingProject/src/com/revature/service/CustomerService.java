package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.DAOCustomerImpl;
import com.revature.pojo.Account;
import com.revature.pojo.User;

public class CustomerService {

	static DAOCustomerImpl daoCust = new DAOCustomerImpl();
	
	/**
	 * Check if username exists
	 * @param un
	 * @return
	 */
	public boolean doesUsernameExist(String un){
		ArrayList<User> users = new ArrayList<User>();
		users = daoCust.getAllUsers();
		
		for(User user : users){
			String temp = user.getUsername();
			if(temp.equals(un)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Try to add customer to DB
	 * @param fn
	 * @param ln
	 * @param un
	 * @param pw
	 * @return
	 */
	public boolean addCustomer(String fn, String ln, String un, String pw){
		if(!doesUsernameExist(un)){
			daoCust.addCustomer(fn, ln, un, pw);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Add account to DB with pending status
	 * @param userId
	 * @param actId
	 * @param typeId
	 * @return
	 */
	public boolean applyForAccount(int userId, int actId, int typeId){
		if((daoCust.applyForAccount(userId, actId, typeId)) == 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Try to Login as customer
	 */
	public int loginCustomer(String username, String password){
		int customerId = 0;//0 = no username found
		int dbCustId = 0;
		String dbPass = "";
		ArrayList<String> customerLoggin = new ArrayList<String>();
		customerLoggin = daoCust.loginCustomer(username, password);
		if(customerLoggin != null){
			dbCustId = Integer.parseInt(customerLoggin.get(0));
			dbPass = customerLoggin.get(1);
			if(password.equals(dbPass)){
				customerId = dbCustId;//login correct
				return customerId;
			}
			else{
				customerId = -1;//password wrong
				return customerId;
			}
		}
		else{
			
		}
		return customerId;
	}
	
	
	/**
	 * Admin login as customer
	 */
	public int loginCustomer(String un){
		int customerId = daoCust.loginCustomer(un);
		return customerId;
	}
	
	/**
	 * Get all accounts of user
	 */
	public ArrayList<Account> getAccounts(int userId){
		ArrayList<Account> accounts = daoCust.getAccounts(userId);
		return accounts;
	}
	
	public String getAccountType(int typeId){
		String actType = daoCust.getAccountType(typeId);
		return actType;
	}

	public String getStatusType(int statusId){
		String statusType = daoCust.getStatus(statusId);
		return statusType;
	}
	
	public double depositMoney(int accountId, double amount){
		double balance = 0;
		
		balance = daoCust.getBalance(accountId);
		balance += amount;
		balance = daoCust.setBalance(accountId, balance);
		
		return balance;
	}
	
	public double withdrawMoney(int accountId, double amount){
		double balance = 0;
		
		balance = daoCust.getBalance(accountId);
		balance -= amount;
		balance = daoCust.setBalance(accountId, balance);
		
		return balance;
	}
	
}
