package com.trms.signupserv;

public class AccountClass {
	
	public String username, email;
	public int empid;
	
	public AccountClass(String username, String email, int empid){
		this.username = username;
		this.email = email;
		this.empid = empid;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public int getEmpid() {
		return empid;
	}

}
