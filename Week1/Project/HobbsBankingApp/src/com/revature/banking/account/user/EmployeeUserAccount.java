package com.revature.banking.account.user;

public class EmployeeUserAccount extends UserAccount {
	
	public EmployeeUserAccount(String username, String password, String email) {
		super(username, password, email);
	}
	
	@Override
	public String toString() {
		return "EmployeeUserAccount: [username=" + this.getUsername() + ", password=" + this.getPassword() + ", email=" + this.getEmail() + "]";
	}

}
