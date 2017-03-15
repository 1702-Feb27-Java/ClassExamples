package com.rev.dao;

import java.util.ArrayList;

import com.rev.pojo.BAccount;
import com.rev.pojo.Users;

public interface Bank {
	String getUser	  	(String uName);
	boolean checkLogin	(String uName, String pWd);
	Users getUserDetails(String uName);
	boolean regUser	  (String fName, String lName, 	 String uName, String sPwd, int iRole);
	boolean createAcc (String uName, int typeAcc, double dAmount);
	boolean updateAcc (String uName, double dAmount);
	boolean deleteAcc (String uName);
	
	BAccount getAccount(String iUserName);
	ArrayList<BAccount> getAccounts(int statusId);
	boolean createAccA(int iAccNo, double dAmt, int statusID,String userName);
	boolean updateApp (int iAccNo, int statusID,double dAmt, String userName);
	boolean deleteAccA(int iAccNo, int statusID,double dAmt, String userName);
}
