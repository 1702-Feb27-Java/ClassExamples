package com.mory;

public class Customer{
	String userName;
	private int pin;
	Account account;
	private  Customer(String userName, int pin, Account account) {
		super();
		this.userName = userName;
		this.pin = pin;
		this.account = account;
	}
	
	public Customer(String userName,int pin){
		this.userName=userName;
		this.pin=pin;
	}
	
	
	
}


	/*
	public String toString() {
		
		return String.format("%s:%d:%s:%d:%b.%d",
				this.account.getAccountype(),
				account.getAccountNumber(),
				getUserName(),
				super.getPassword(),
				active,
				this.balance);
				*/
				
	

	

