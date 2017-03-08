package com.mory.RevatureBankingApp;

public class Customer {
	private String name;
	private int pin;
	private Account Account;
	
	/**
	 * Constructor to add a customer
	 * 
	 * @param name Customer's name
	 * @param pin  customer's pin
	 * @param account customer's Account
	 */
	public Customer(String name, int pin,Account account) {
		super();
		this.name = name;
		this.pin = pin;
		Account = account;
	}
	
	
	public Customer(String name, int pin) {
		super();
		this.name = name;
		this.pin = pin;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public Account getAccount() {
		return Account;
	}
	public void setAccount(Account account) {
		Account = account;
	}
	
	
	
	

}
