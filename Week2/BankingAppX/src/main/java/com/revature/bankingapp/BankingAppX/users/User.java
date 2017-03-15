package com.revature.bankingapp.BankingAppX.users;

import com.revature.bankingapp.BankingAppX.account.Account;

//Using this user class as the pojo class

public class User
{
	String firstName, lastName, userName, password, adminPin;
	int role, userid;

	Account account = new Account();
	
	public User()
	{
		super();
	}
	
	public User(String fn, String ln, String un, String pw, int rl, int uid)
	{
		super();
		this.firstName = fn;
		this.lastName = ln;
		this.userName = un;
		this.password = pw;
		this.role = rl;
		this.userid = uid;
		this.account.setAccountid(userid);
	}
	
	public boolean getActive()
	{
		return account.isActive();
	}
	public void setActive(boolean flag)
	{
		this.account.setActive(flag);
	}
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
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

	public int getRole()
	{
		return role;
	}

	public void setRole(int role)
	{
		this.role = role;
	}
	
	public int getUserID()
	{
		return userid;
	}
	
	public void setUserID(int userid)
	{
		this.userid = userid;
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
	
	public void setAccountBalance(double bal)
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
	
	public String getAdminPin()
	{
		return adminPin;
	}

	public void setAdminPin(String adminPin)
	{
		this.adminPin = adminPin;
	}
	
	@Override
	public String toString()
	{
		return "User [User ID=" + userid + " UserName=" + userName + ", Password=" + password + ", Role=" + role + "]";
	}
}
