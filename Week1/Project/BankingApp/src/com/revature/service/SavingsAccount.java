package com.revature.service;

import java.io.IOException;

// class for creating a savings account object
public class SavingsAccount {
	
	// has member, constructor, getters and setters
	private double balance;
	
	public SavingsAccount()
	{
		balance = 0;
	}

	public SavingsAccount(double initialBalance)
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
