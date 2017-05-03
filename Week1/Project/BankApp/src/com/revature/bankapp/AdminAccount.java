package com.revature.bankapp;

public class AdminAccount extends UserAccount {

	private final int ACCESS_LEVEL = 2;
	
	public AdminAccount(String username, String password, Person person) {
		super(username, password, person);
		String userN = ""+userNum;
		accountNumber = accountNumber.replace((accountNumber.length() - userN.length()), accountNumber.length(), userN);
		
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public StringBuffer getAccountNumber() {
		return accountNumber;
	}
	
	public int getAccessLevel(){
		return ACCESS_LEVEL;
	}
}
