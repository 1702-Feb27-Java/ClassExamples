package com.revature.service;

import java.util.ArrayList;
import com.revature.dao.DAOCustomerImpl;
import com.revature.dao.DAOEmployeeImpl;
import com.revature.pojo.Account;

public class EmployeeService {
	static CustomerService serve = new CustomerService();
	static DAOCustomerImpl daoCust = new DAOCustomerImpl();
	static DAOEmployeeImpl daoEmp = new DAOEmployeeImpl();
	
	/**
	 * Tries to add employee to DB
	 * @param fn
	 * @param ln
	 * @param un
	 * @param pw
	 * @return
	 */
	public boolean addEmployee(String fn, String ln, String un, String pw){
		if(!serve.doesUsernameExist(un)){
			daoEmp.addEmployee(fn, ln, un, pw);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Tries to log in as employee
	 * @param username
	 * @param password
	 * @return
	 */
	public int loginEmployee(String username, String password){
		int employeeId = 0;//0 = no username found
		int dbEmpId = 0;
		String dbPass = "";
		ArrayList<String> employeeLoggin = new ArrayList<String>();
		employeeLoggin = daoEmp.loginEmployee(username, password);
		if(employeeLoggin != null){
			dbEmpId = Integer.parseInt(employeeLoggin.get(0));
			dbPass = employeeLoggin.get(1);
			if(password.equals(dbPass)){
				employeeId = dbEmpId;//login correct
				return employeeId;
			}
			else{
				employeeId = 1;//password wrong
				return employeeId;
			}
		}
		return employeeId;
	}

	/**
	 * Gets all accounts that are unapproved
	 * @return
	 */
	public ArrayList<Account> getUnapprovedAccounts(){
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = daoEmp.getUnapprovedAccounts();
		return accounts;
	};

	/**
	 * Edit the status of an account to approved/denied
	 * @param actId
	 * @param statusId
	 * @return
	 */
	public boolean editAccountStatus(int actId, int statusId){
		boolean editConfirm = daoEmp.editAccountStatus(actId, statusId);
		return editConfirm;
	}
	
	/**
	 * set the resolver field for when you approve/decline account
	 * @param empId
	 * @param actId
	 * @return
	 */
	public boolean setResolverId(int empId, int actId){
		boolean confirmation = daoEmp.setResolverId(empId, actId);		
		return confirmation;
		
	}

	/**
	 * Get accounts based on your employee id
	 * @param empId
	 * @return
	 */
	public ArrayList<Account> getAccounts(int empId){
		ArrayList<Account> accounts = daoEmp.getAccounts(empId);
		return accounts;
	}
	
	/**	 
	 * Login as admin
	 * @param un
	 * @param pw
	 * @return
	 */
	public boolean loginAdmin(String un, String pw){
		String password = daoEmp.adminLogin(un, pw);
		if(pw.equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
}
