package com.mory;

public class Customer {
	Account account;
	Boolean isActive;
	double balance;
	String name;
	int password;

	public Customer(String name, int password, double balance) {
		super();
		this.balance = balance;
		this.name = name;
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public Boolean getActive() {
		return isActive;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("%d",account.getAccountNumber());

	}
	
	/*String.format("%d:%s:%d:%d:%s:%b", account.getAccountNumber(), getName(), getPassword(), getBalance(),
			account.getAccountype(),getActive());*/

}
