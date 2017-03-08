package com.banksd.bank;

import com.banksd.BankServices;

public class Account {
	long accNo;
	String accType;
	double accBal;
	
	public boolean linkAcc(){
		return true;
	}
	
	public Account(long accNumber, String iacctype,double iBal){
		this.accNo = accNumber;
		this.accBal= iBal;
		this.accType=iacctype;
	}
	
	public double getBalance(double iAmount){
		return this.accBal-=iAmount;
	}
	
	public double getBalance(){
		return this.accBal;
	}
	
	public void setBalance(double iAmount){
		this.accBal+=iAmount;
	}
	
	public static long genAcc(){
		return ((long) ((Math.random()) * 1000))*System.nanoTime();
	}
	
	public void setType(String iType){
		this.accType=iType;
	}
}
