package com.mory;

public class Employee extends User {
	
	/// MetChod to for Banker to approve customer account
	public void approve(Account account){
		account.setApproved(true);
	}


	public Employee(String userName, int Password) {
		super(userName, Password);
		// TODO Auto-generated constructor stub
	}
	

}
