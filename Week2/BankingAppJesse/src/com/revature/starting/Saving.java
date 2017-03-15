package com.revature.starting;

public class Saving {

	private double balance;

	public Saving() {

		this.balance = 0;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void withdraw(double amount) throws Exception {
		if ((balance - amount) < 0) {
			throw new Exception();
		} else if (amount < 0) {
			throw new Exception();

		}
		this.balance = this.balance - amount;

	}

	public void deposit(double amount) throws Exception {
		if (amount < 0) {
			throw new Exception();
		}
		this.balance = this.balance + amount;

	}
}
