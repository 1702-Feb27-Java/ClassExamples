package com.revature.bankingapp.BankingAppX.account;

public class Account
{
	String acctType, username;
	double acctBalance;
	int accountid, resolverid;
	boolean isActive = true;

	public int getAccountid()
	{
		return accountid;
	}
	public void setAccountid(int accountid)
	{
		this.accountid = accountid;
	}
	public double getAcctBalance()
	{
		return acctBalance;
	}
	public void setAcctBalance(double acctBalance)
	{
		this.acctBalance = acctBalance;
	}
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
	public int getResolverID()
	{
		return resolverid;
	}
	public void setResolverID(int id)
	{
		this.resolverid = id;
	}
}
