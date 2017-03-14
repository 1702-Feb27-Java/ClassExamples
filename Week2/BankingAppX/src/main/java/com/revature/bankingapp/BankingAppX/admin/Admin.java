package com.revature.bankingapp.BankingAppX.admin;
import com.revature.bankingapp.BankingAppX.users.User;

public class Admin extends User
{
	String adminPin;

	public String getAdminPin()
	{
		return adminPin;
	}

	public void setAdminPin(String adminPin)
	{
		this.adminPin = adminPin;
	}
}
