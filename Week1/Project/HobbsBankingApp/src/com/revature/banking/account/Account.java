package com.revature.banking.account;

import java.io.Serializable;

import com.revature.banking.account.BankAccount.Status;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -843247695419539887L;
	
	private BankAccount checking;
	private BankAccount savings;
	
	public Account() {
		this.checking = new BankAccount();
		this.savings = new BankAccount();
	}
	
	public void depositChecking(double amount) {
		this.checking.deposit(amount);
	}
	
	public void depositSavings(double amount) {
		this.savings.deposit(amount);
	}
	
	public double withdrawChecking(double amount) {
		return this.checking.withdraw(amount);
	}
	
	public double withdrawSavings(double amount) {
		return this.savings.withdraw(amount);
	}
	
	public Status checkCheckingStatus() {
		return this.checking.getStatus();
	}
	
	public Status checkSavingsStatus() {
		return this.savings.getStatus();
	}
	
	public void openChecking() {
		this.checking.setStatus(Status.pending);
	}
	
	public void openSavings() {
		this.savings.setStatus(Status.pending);
	}
	
}
