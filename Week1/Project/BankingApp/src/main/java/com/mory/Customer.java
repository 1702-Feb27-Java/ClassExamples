package com.mory;

import java.util.List;

public class Customer extends User{
	Account account;
	Boolean active;
	String accountType;
	double deposit;
	public Customer(String userName, int Password,String accountType,double deposit) {
		super(userName, Password);
		account=new Account(accountType,deposit);
		
		// TODO Auto-generated constructor stub
	}

	public Customer(String userName, int password, String accountType, double deposit, boolean isActive) {
		super(accountType, password);
		this.userName=userName;
		this.password=password;
		this.accountType=accountType;
		this.deposit=deposit;
		active=isActive;
		account=new Account(accountType,deposit);
	}

	public Account getAccount() {
		
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		
		return String.format("%s:%d:%s:%d:%b",
				this.account.getAccountype(),
				account.getAccountNumber(),
				getUserName(),
				super.getPassword(),
				active);
				
	}
}
