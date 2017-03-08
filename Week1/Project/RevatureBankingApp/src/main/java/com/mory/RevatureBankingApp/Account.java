package com.mory.RevatureBankingApp;

public class Account {
	public enum AccountType{
		CHECKIN,SAVING;
	}
	
	private int accountNumber;
	private static int baseAccountNumber=499;
	private AccountType accountType;
	private double balance;
	
	/**
	 * 
	 * @param accountType Type of account (checking or savings)
	 * @param balance account balance
	 */
	public Account(AccountType accountType, double balance) {
		super();
		this.accountNumber =baseAccountNumber++;
		this.accountType = accountType;
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}
/**
 *  set the account's type
 * 
 * @param accountType Account Type
 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
	

}
