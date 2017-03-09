package com.revature.banking.account.user;

import java.util.ArrayList;

import com.revature.banking.account.Account;

public class CustomerUserAccount extends UserAccount {
	
	private ArrayList<Account> accounts;

	public CustomerUserAccount(String username, String password, String email) {
		super(username, password, email);
		
		this.accounts = new ArrayList<>();
	}
	
	public ArrayList<Account> getAccounts() {
		return this.accounts;
	}
	
	public void openNewAccount() {
		this.accounts.add(new Account());
	}
	
	public void openCheckingAccount(int accountNumber) { 
		this.accounts.get(accountNumber).openChecking();
	}
	
	public void openSavingsAccount(int accountNumber) { 
		this.accounts.get(accountNumber).openSavings();
	}
	
	@Override
	public String toString() {
		return "CustomerUserAccount: [username=" + this.getUsername() + ", password=" + this.getPassword() + ", email=" + this.getEmail() + ", number of accounts = " + this.accounts.size()  + ": { " + this.accounts + " } ]";
	}
	
}
