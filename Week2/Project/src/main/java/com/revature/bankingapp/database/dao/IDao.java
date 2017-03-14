package com.revature.bankingapp.database.dao;

import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;

import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.AccountStatus;
import com.revature.bankingapp.database.model.AccountType;
import com.revature.bankingapp.database.model.LogLevel;
import com.revature.bankingapp.database.model.Role;
import com.revature.bankingapp.database.model.User;

public interface IDao {
	void saveNewUser(User newUser);
	void createNewAccount(User user, Account newAccount);
	boolean addUserToAccount(User user, Account account);
	User getUser(Integer userId);
	User getUser(String username);
	boolean deleteUser(User user);
	boolean deleteAccount(Account account);
	Account getAccount(Integer id);
	List<Account> getAccountFromUser(User user);
	Map<Integer, Role> getUserRoles();
	Map<Integer, AccountType> getAccountTypes();
	Map<Integer, AccountStatus> getAccountStatuses();
	boolean updateUser(User user);
	boolean updateAccount(Account account);
	List<Account> getAllAccounts();
	List<User> getAllUsers();
	List<LogLevel> getLogLevels();
	void log(String message, LogLevel level, Integer userId);
	

}
