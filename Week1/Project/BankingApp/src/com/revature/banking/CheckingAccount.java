package com.revature.banking;

import java.io.IOException;

// this is a class for a checking account
public class CheckingAccount {
	
	// with balance member, constructors, and getters and setters
	private double balance;
	
	public CheckingAccount()
	{
		balance = 0;
	}

	public CheckingAccount(double initialBalance)
	{
		balance = initialBalance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void deposit(double amount){
		balance = balance + amount;
	}
	
	public void withdraw(double amount){
		balance = balance - amount;
	}
	

}
