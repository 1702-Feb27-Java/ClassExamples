package com.revature.bankingapp.model;

public class Account {
	
	private int id;
	private String accountName;
	private String accountType;
	private double balance;
	private boolean isApproved;
	
	public Account(String accountName, String accountType, double balance, boolean isApproved){
		this(0, accountName, accountType, balance, isApproved);
	}
	
	public Account(int id, String accountName, String accountType, double balance, boolean isApproved){
		this.id = id;
		this.accountName = accountName;
		this.accountType = accountType;
		this.balance = balance;
		this.isApproved = isApproved;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double d) {
		this.balance = d;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountName=" + accountName + ", accountType=" + accountType + ", balance="
				+ balance + ", isApproved=" + isApproved + "]";
	}
	
	
}
