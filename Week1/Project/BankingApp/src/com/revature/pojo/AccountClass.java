package com.revature.pojo;

public class AccountClass {
	
	private int accountID;
	private int typeID;
	private double balance;
	private int statusID;
	private int resolverID;
	
	
	public AccountClass(int accountID, int typeID, double balance, int statusID, int resolverID){
		super();
		this.accountID = accountID;
		this.typeID = typeID;
		this.balance = balance;
		this.statusID = statusID;
		this.resolverID = resolverID;
	}
	
	public AccountClass(){
		
	}
	
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getResolverID() {
		return resolverID;
	}
	public void setResolverID(int resolverID) {
		this.resolverID = resolverID;
	}

	@Override
	public String toString() {
		return "[accountID=" + accountID + ", typeID=" + typeID + ", balance=" + balance + ", statusID="
				+ statusID + ", resolverID=" + resolverID + "]";
	}
	
}
