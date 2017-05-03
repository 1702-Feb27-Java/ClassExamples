package com.revature.banking2.app;

import java.util.ArrayList;

import com.revature.banking2.pojo.Account;
import com.revature.banking2.pojo.User;

public class State {
	
	private static State state;
	
	/**
	 * The user currently logged in.
	 * 
	 */
	private User user;
	private ArrayList<User> users;
	/**
	 * Copy of user from users being edited.
	 * 
	 */
	private User workingUser;
	/**
	 * Indexes users.
	 * 
	 */
	private int workingUserIndex;
	private ArrayList<Account> accounts;
	private int workingAccountIndex;
	
	private State() {
		
	}
	
	public static State getState() {
		if (state == null) {
			state = new State();
		}
		return state;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public int getWorkingUserIndex() {
		return this.workingUserIndex;
	}
	
	public void setWorkingUserIndex(int usersIndex) {
		this.workingUserIndex = usersIndex;
	}
	
	public User getWorkingUser() {
		return this.workingUser;
	}
	
	public void setWorkingUser(User workingUser) {
		this.workingUser = workingUser;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	
	public ArrayList<Account> getAccounts() {
		return this.accounts;
	}

	public int getWorkingAccountIndex() {
		return workingAccountIndex;
	}

	public void setWorkingAccountIndex(int workingAccountIndex) {
		this.workingAccountIndex = workingAccountIndex;
	}
	
}
