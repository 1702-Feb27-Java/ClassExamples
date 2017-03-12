package com.revature.pojo;

public class Account {
	int accountId, typeId, statusId, resolverId;
	double balance;
	
	public Account(int accountId, int typeId, int statusId, int resolverId, double balance) {
		super();
		this.accountId = accountId;
		this.typeId = typeId;
		this.statusId = statusId;
		this.resolverId = resolverId;
		this.balance = balance;
	}
	
	public Account(){
		
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
		return "Account [accountId=" + accountId + ", typeId=" + typeId + ", statusId=" + statusId + ", resolverId="
				+ resolverId + ", balance=" + balance + "]";
	}
	
	
	
}
