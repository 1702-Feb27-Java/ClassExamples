package dao;

import java.util.ArrayList;

import pojo.Account;

public interface CustomerDAO {
	
	void addCustomer(String firstName, String lastName, String userName, String password);
	
	
	void openAnAccount(int userId, int typeId, int statusId);

	public double getBalance(int accountId);
	
	public String getAccountType(int typeid);
	
	public String getAccountStatus(int statusId);
	public void deposit(int accntid,double amount);
	
	public void withdraw(int accntid,double amount);
	
	public int checkLogin(String username, String password);
	
	public boolean checkIfUserNameExist(String password);
	
	
	
	
	
	

}
