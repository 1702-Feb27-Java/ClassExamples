package com.revature.bankapp;

import java.io.Serializable;

public class SavingAccount implements BankingInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String accountNumber;
	double balance = 0;
	boolean requested = false;
	boolean approved = false;
	
	
	public SavingAccount(String accountNumber){
		this.accountNumber = accountNumber;
	}
	
	public SavingAccount(double balance){
		this.balance = balance;
	}
	public SavingAccount(String accountNumber, int balance){
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	@Override
	public void deposit(double amount) {
		balance += amount;
		System.out.println("Current balance: " + balance);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(double amount) {
		if(amount > balance){
			System.out.println("INSUFFICIENT FUNDS");
		}else
		{
			balance -= amount;
			System.out.println("Current balance: " + balance);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
	
	@Override
	public String toString(){
		return "" + balance;
	}

	public void setBalance(double balance) {
this.balance = balance;		
	}


}
