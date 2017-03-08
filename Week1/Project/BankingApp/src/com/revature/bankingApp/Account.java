package com.revature.bankingApp;

public class Account {
	
	private double Balance;
	private String AccountType;
	
	public Account() {
		Balance = 0.0;
		setAccountType("");
	}
	
	public Account(String Acct_Type) {
		Balance = 0.0;
		setAccountType(Acct_Type);
	}
	
	public void deposit(double amount) {
		if( amount != 0)
			Balance += amount;
		else
			System.out.println("Enter an amount greater than zero");
	}
	
	public void withdraw(double amount) {
		if( amount != 0 )
			Balance -= amount;
		else if ( Balance == 0)
			System.out.println("You can not take from a balence that is zero");
	}
	
	public double getBalance() {
		return Balance;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	
	
	
	

}
