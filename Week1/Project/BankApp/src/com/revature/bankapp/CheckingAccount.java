package com.revature.bankapp;

import java.io.Serializable;

public class CheckingAccount implements BankingInterface,Serializable{

	String accountNumber;
	double balance = 0;
	boolean requested = false;
	boolean approved = false;
	
	
	public CheckingAccount(String accountNumber){
		this.accountNumber = accountNumber;
	}
	public CheckingAccount(double balance){
		this.balance = balance;
	}
	public CheckingAccount(String accountNumber, int balance){
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	@Override
	public void deposit(double amount) {
		balance += amount;
		System.out.println("Current balance : " + balance);
	}
	@Override
	public void withdraw(double amount) {
		balance -= amount;
		
	}
	@Override
	public double getBalance() {
			return balance;
	}
	
	public boolean getRequested(){
		return requested;
	}
	
	public boolean getApproved(){
		return approved;
	}
	
	//overrided for easy display to employees
	@Override
	public String toString(){
		return ""+balance;
	}
	public void setBalance(double balance) {
this.balance 	= balance;	
	}
	


}
