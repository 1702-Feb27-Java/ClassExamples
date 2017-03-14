package com.revature.bankingapp.BankingAppX.dao;

import java.util.ArrayList;
import com.revature.bankingapp.BankingAppX.account.Account;
import com.revature.bankingapp.BankingAppX.users.User;

public interface DAO
{
	int addUser(String firstName, String lastName, String username, String password, int role);
	int addAdmin(String firstName, String lastName, String username, String password, int role, String apin);
	int updateFirstName(User user, String firstname);
	int updateLastName(User user, String lastname);
	int updateUserName(User user, String username);
	int updatePassWord(User user, String password);
	User getUserByUsername(String username);
	ArrayList<User> getAllUsers();
	int deposit(String username, Double bal);
	int withdrawl(String username, Double bal);
	int addAccount(User user);
	ArrayList<Account> getPending();
	void validatePending(User user, Account account);
}