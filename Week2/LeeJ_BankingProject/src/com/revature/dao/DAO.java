package com.revature.dao;

import java.util.ArrayList;

import com.revature.banking.Account;
import com.revature.banking.User;


public interface DAO {
	
	// NOTE: Admin simply has functionalities of employee and customer
	
	// returns user_id if username and password match up
	public int login(String uName, String pw);
	
	// adding records
	int addAdmin(String fName, String lName, String uName, String pw);
	int addEmployee(String fName, String lName, String uName, String pw);
	int addCustomer(String fName, String lName, String uName, String pw);
	int addAccount(int uId, int typeId, int statusId);
	
	// getting records
	User getUser(int uId);
	Account getAccount(int aId);
	ArrayList<Integer> getAllPendingAccountIds();
	// admin get list of all customers
	ArrayList<User> getAllCustomers();
	// admin get list of all employees
	ArrayList<User> getAllEmployees();
	
	// update records
	int updateStatus(int aId, int sId);
	int updateBalance(int aId, double bal);
	int approve(int uId, int aId);
	int decline(int uId, int aId);

}
