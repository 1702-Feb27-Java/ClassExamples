package com.revature.pojo;

public class Accounts {
	
	private int Account_ID;
	private int Type_ID;
	private int Balance;
	private int Status_ID;
	private int Resolver;
	
	//constructors
	public Accounts(int account_ID, int type_ID, int balance, int status_ID, int resolver) {
		super();
		Account_ID = account_ID;
		Type_ID = type_ID;
		Balance = balance;
		Status_ID = status_ID;
		Resolver = resolver;
	}

	//getters and setters
	public int getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(int account_ID) {
		Account_ID = account_ID;
	}

	public int getType_ID() {
		return Type_ID;
	}

	public void setType_ID(int type_ID) {
		Type_ID = type_ID;
	}

	public int getBalance() {
		return Balance;
	}

	public void setBalance(int balance) {
		Balance = balance;
	}

	public int getStatus_ID() {
		return Status_ID;
	}

	public void setStatus_ID(int status_ID) {
		Status_ID = status_ID;
	}

	public int getResolver() {
		return Resolver;
	}

	public void setResolver(int resolver) {
		Resolver = resolver;
	}

	@Override
	public String toString() {
		return "Accounts [Account_ID=" + Account_ID + ", Type_ID=" + Type_ID + ", Balance=" + Balance + ", Status_ID="
				+ Status_ID + ", Resolver=" + Resolver + "]";
	}
	
	
	
	
}
