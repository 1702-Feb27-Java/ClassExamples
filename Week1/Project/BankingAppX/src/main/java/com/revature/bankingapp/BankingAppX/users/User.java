package com.revature.bankingapp.BankingAppX.users;

import com.revature.bankingapp.BankingAppX.account.Account;

public class User
{
	String userName, password, role;
	Account account = new Account();
	
	public boolean getActive()
	{
		return account.isActive();
	}
	public void setActive(boolean flag)
	{
		this.account.setActive(flag);
	}
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}
	
	public String getAccountType()
	{
		return account.getAcctType();
	}
	
	public void setAccountType(String accountType)
	{
		this.account.setAcctType(accountType);
	}
	
	public double getAccountBalance()
	{
		return account.getAcctBalance();
	}
	
	public void setAccountBalace(double bal)
	{
		this.account.setAcctBalance(bal);
	}
	
	public String getAccountUsername()
	{
		return account.getUsername();
	}
	
	public void setAccountUsername()
	{
		this.account.setUsername(userName);
	}
}
