package com.revature.bankapp;

public class BankAccount implements BankingInterface {

	String accountNumber;
	double balance = 0;
	
	
	public BankAccount(String accountNumber){
		this.accountNumber = accountNumber;
	}
	public BankAccount(String accountNumber, int balance){
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	@Override
	public void deposit(double amount) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void withdraw(double amount) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double getBalance() {
		return balance;
		
	}
	
	

}
