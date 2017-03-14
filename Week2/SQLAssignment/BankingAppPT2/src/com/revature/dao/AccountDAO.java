package com.revature.dao;

import com.revature.pojo.Accounts;
import com.revature.pojo.Users;

public interface AccountDAO {

	//abstract methods for the users account
	public void applyForAccount(Users usr);
	public void ViewAccountInfo(Users usr);
	
	//public void DepositIntoAccount(int user_id, int Account_id, int number);
	//public void WithdrawFromAccount(int user_id, int Account_id, int number);
	
	
	
}
