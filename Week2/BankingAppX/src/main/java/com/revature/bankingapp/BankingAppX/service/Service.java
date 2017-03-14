package com.revature.bankingapp.BankingAppX.service;

import java.util.ArrayList;

import com.revature.bankingapp.BankingAppX.account.Account;
import com.revature.bankingapp.BankingAppX.admin.Admin;
import com.revature.bankingapp.BankingAppX.dao.DAOImpl;
import com.revature.bankingapp.BankingAppX.users.User;

public class Service
{
	static DAOImpl dao = new DAOImpl();
	
	public void accountDeposit(String user, double dep)
	{
		User userTemp = new User();
		userTemp = dao.getUserByUsername(user);
		dao.deposit(user, dep);
		System.out.println("Money Deposited! New Balance: " + userTemp.getAccountBalance());
	}
	
	public void accountWithdrawl(String user, double wit)
	{
		User userTemp = new User();
		userTemp = dao.getUserByUsername(user);
		dao.withdrawl(user, wit);
		System.out.println("Money Withdrew! New Balance: " + userTemp.getAccountBalance());
	}
	
	public User viewCertainAccountForAdmin(String username)
	{
		User admin = new Admin();
		admin = dao.getUserByUsername(username);
		return admin;
	}
	
	public User viewCertainAccount(String username)
	{
		User user = new User();
		user = dao.getUserByUsername(username);
		return user;
	}
	
	public void viewAllAccounts()
	{
		ArrayList<User> tempArray = new ArrayList();
		tempArray = dao.getAllUsers();
		for(User u: tempArray)
		{
			u.toString();
		}
	}
	
	public void addUser(String fn, String ln, String un, String pa, int r)
	{
		dao.addUser(fn, ln, un, pa, r);
		System.out.println("New user created!");
	}
	
	public void addAdmin(String fn, String ln, String un, String pa, int r, String adminPin)
	{
		dao.addAdmin(fn, ln, un, pa, r, adminPin);
		System.out.println("New admin created!");
	}
	
	public void addAccount(User user)
	{
		dao.addAccount(user);
		System.out.println("New account created!");
	}
	
	public void updateName(User user, String fn, String ln)
	{
		dao.updateFirstName(user, fn);
		dao.updateLastName(user, ln);
		System.out.println("Updated: " + fn + " " + ln);
	}
	
	public void updateAccountCredentials(User user, String un, String pw)
	{
		dao.updateUserName(user, un);
		dao.updatePassWord(user, pw);
		System.out.println("Updated: " + un + " " + pw);
	}
	
	public ArrayList<Account> viewPendingAccounts()
	{
		ArrayList<Account> temp = new ArrayList();
		temp = dao.getPending();
		for(Account a : temp)
		{
			a.toString();
		}
		return temp;
	}
	
	public void validateAccounts(User user, Account account)
	{
		dao.validatePending(user, account);
		System.out.println("Account validated!");
	}
}
