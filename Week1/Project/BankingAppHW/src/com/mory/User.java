package com.mory;

import java.util.ArrayList;

public class User { 
	//user firstName
	private String firstName;
	private String lastName;
	// Unique universal identifier
	private String uuid;
	//User's pin
	private int pin;
	//  a list of its accounts for this user
	private ArrayList<Account> accounts;
	
	/***
	 *  Create a new User
	 * @param firstName the user's first name
	 * @param lastName the user's last name
	 * @param uuid the user's account
	 * @param pin the user's pin
	 * @param accounts the bank object that the user is a customer of
	 * @param theBank
	 */
	public User(String firstName, String lastName,  int pin,Bank theBank) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.uuid = theBank.getNewUserUUID();
		this.pin = pin;
		
		// create empty list of accounts
		this.accounts =  new ArrayList<Account>();
		System.out.printf("New user %s,%s with id %s",firstName,lastName,uuid);
		
	}
	
	/**
	 * Add an account for the user
	 * 
	 * @param account the account to add 
	 */

	public void addAccount(Account account){
		this.accounts.add(account);
	}
/**
 * Return the unique id for the user
 * 
 * @return the uuid
 */
	public String getUUID() {
		// TODO Auto-generated method stub
		return this.uuid;
	}
/**
 * Check the validity of user's pin;
 * @return
 */
public boolean validatePin( int pin) {
	return this.pin==pin;
}
	
	
	
	
	

	
}
