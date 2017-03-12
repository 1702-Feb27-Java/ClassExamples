package com.revature.pojo;

public class Account {
	int accountId, statusId, resolverId, typeId;
	double balance;

	public Account(int accountId, int typeId, double balance, int statusId, int resolverId) {
		super();
		this.accountId = accountId;
		this.statusId = statusId;
		this.resolverId = resolverId;
		this.balance = balance;
		this.typeId = typeId;
	}

	public Account(){
		
	}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", statusId=" + statusId + ", resolverId=" + resolverId
				+ ", balance=" + balance + ", typeId=" + typeId + "]";
	}
	
	

}
