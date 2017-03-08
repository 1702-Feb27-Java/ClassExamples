package com.revature.banking.account.user;

public class AdminUserAccount extends UserAccount {

	public AdminUserAccount(String username, String password, String email) {
		super(username, password, email);
	}
	
	@Override
	public String toString() {
		return "AdminUserAccount: [username=" + this.getUsername() + ", password=" + this.getPassword() + ", email=" + this.getEmail() + "]";
	}
	
}
