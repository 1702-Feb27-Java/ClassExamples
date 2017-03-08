package com.revature.banking.account;

import java.io.Serializable;

public class BankAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5218142803515234158L;

	public enum Status {
		pending, approved, denied, closed
	}

	private double balance;
	private Status status;
	
	public BankAccount() {
		this.balance = 0;
		this.status = Status.pending;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public double withdraw(double amount) {
		balance -= amount;
		return balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
