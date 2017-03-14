package com.revature.banking;

public class Account {

	private int accountId = 0;
	private int typeId;
	private int statusId;
	private int resolverId;
	private double balance = 0;

	public Account() {
		// TODO Auto-generated constructor stub
	}	
	
	public Account(int aId, int tId, int sId) {
		accountId = aId;
		typeId = tId;
		statusId = sId;
	}
	public Account(int aId, int tId, int sId, double bal) {
		accountId = aId;
		typeId = tId;
		statusId = sId;
		balance = bal;
	}
	public Account(int aId, int tId, double bal, int sId, int rId) {
		accountId = aId;
		typeId = tId;
		balance = bal;
		statusId = sId;
		resolverId = rId;
	}

	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return ((typeId < 2) ? "Checkings" : "Savings") + " Account ID# " + accountId + "\nCurrent Balance: " + balance + "\nAccount Status: " 
				+ ((statusId != 2) ? "Unapproved" : "Approved");
	}

}
