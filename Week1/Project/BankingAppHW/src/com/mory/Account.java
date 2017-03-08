package com.mory;

import java.util.ArrayList;

public class Account {
	// The name of the account
	private String name;
	// the account's Id number
	private String uiid;
	// the account's balance
	
	private User holder;
	
	// account's list of transactions for this account
	private ArrayList<Transaction>  transactions;
	
	/***
	 * Create a new Account
	 * @param name the name of the account
	 * @param holder the user object that holds this account
	 * @param theBank the bank that issues the account
	 */

	public Account(String name, User holder,Bank theBank) {
	  // set the account name and holder
		this.name = name;
		this.holder = holder;
		//The new account UUID
		this.uiid=theBank.getnewAccountUUID();
		// initialize the transaction to an empty list
		this.transactions= new ArrayList<Transaction>();
		
	}

/**
 * return the account's unique id
 * 
 * @return the unique
 */
	public String getUUID() {
		
		return this.uiid;
	}
	
	
	

}
