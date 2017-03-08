package com.mory;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

	private String name;
	private ArrayList<User> users;
	
	/** 
	 * create a new bank object with empty lists of users and accounts
	 * @param name the name of the bank
	 */
	public Bank(String name){
		this.name=name;
		this.users=new ArrayList<User>();
		this.accounts=new ArrayList<Account>();
	}

	private ArrayList<Account> accounts;

	/**
	 * generate a new unique if for the user
	 * 
	 * @return the unique id
	 */
	public String getNewUserUUID() {
		String uuid;
		Random rand = new Random();
		int len = 4;
		boolean nonUnique;
		do {
			uuid = "";
			for (int i = 0; i < len; i++) {
				uuid += ((Integer) rand.nextInt(10)).toString();
			}
			nonUnique = false;
			for (User user : this.users) {
				if (uuid.compareTo(user.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}

		} while (nonUnique);

		return uuid;
	}

	/**
	 * Add an account
	 * 
	 * @param accnt
	 *            the account to add
	 */
	public void addAccount(Account accnt) {
		this.accounts.add(accnt);
	}

	/**
	 * Generate a new unique id for the account
	 * 
	 * @return the unique id
	 */
	public String getnewAccountUUID() {
		String uuid;
		Random rand = new Random();
		int len = 8;
		boolean nonUnique;
		do {
			uuid = "";
			for (int i = 0; i < len; i++) {
				uuid += ((Integer) rand.nextInt(10)).toString();
			}
			nonUnique = false;
			for (Account account : this.accounts) {
				if (uuid.compareTo(account.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}

		} while (nonUnique);

		return uuid;
	}

	public User addUser(String firstnName, String lastName, int pin) {
		// Create a new user object and add it to our list
		User user = new User(firstnName, lastName, pin, this);
		this.users.add(user);

		// automatically create a savings account for the user
		Account accnt = new Account("Savings", user, this);
		// add account to holder and bank lists
		user.addAccount(accnt);
		// add accout to acounts list
		this.accounts.add(accnt);

		return user;
	}
	/***
	 * returs the user object associated with a particular userid and pin
	 * @param userId
	 * @param pin
	 * @return
	 */
	public User userLogin(String userId,int pin){
		// search list of users
		for(User user: this.users){
			if(user.getUUID().compareTo(userId)==0 && user.validatePin(pin)){
				return user;
			}
			
		}
		// incorrect user info
		return null;
		
	}
}
