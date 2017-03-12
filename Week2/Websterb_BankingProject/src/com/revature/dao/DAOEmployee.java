package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojo.Account;
import com.revature.util.ConnectionUtil;

public interface DAOEmployee {
	int addEmployee(String fn, String ln, String un, String pw); 
	
	ArrayList<String> loginEmployee(String un, String pw);
	
	ArrayList<Account> getUnapprovedAccounts();
	
	public boolean editAccountStatus(int actId, int status);
	
	public boolean setResolverId(int empId, int actId);
	
	ArrayList<Account> getAccounts(int empId);
	
	public boolean adminLogin(String un, String password);
	
}
