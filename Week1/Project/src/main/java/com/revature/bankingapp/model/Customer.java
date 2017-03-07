package com.revature.bankingapp.model;

import java.util.LinkedList;
import java.util.List;

public class Customer extends User {
	private List<Integer> accountIds;
	
	Customer(int id, String username, String password, List<Integer> accountIds) {
		super(id, username, password);
		this.accountIds = accountIds;
	}

	public Customer(String username, String password) {
		super(username, password);
		this.accountIds = new LinkedList<Integer>();
	}

	

	public List<Integer> getAccountIds() {
		return accountIds;
	}

	public void setAccounts(List<Integer> accountIds) {
		this.accountIds = accountIds;
	}

	@Override
	public String toString() {
		return "Customer [accountIds=" + accountIds + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + "]";
	}

	@Override
	public String getUserType() {
		return "Customer";
	}
	
	
}
