package com.revature.bankingapp.BankingAppX.account;

public class Account
{
	String acctType, username;
	double acctBalance;
	public double getAcctBalance()
	{
		return acctBalance;
	}
	public void setAcctBalance(double acctBalance)
	{
		this.acctBalance = acctBalance;
	}
	boolean isActive = true;
	public boolean isActive()
	{
		return isActive;
	}
	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getAcctType()
	{
		return acctType;
	}
	public void setAcctType(String acctType)
	{
		this.acctType = acctType;
	}
}
