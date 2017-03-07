package com.revature.bankingapp.model;

public class Employee extends User {
	public Employee(int id, String username, String password, boolean isAdmin) {
		super(id, username, password);
		this.isAdmin = isAdmin;
	}
	public Employee(String username, String password, boolean isAdmin) {
		super(username, password);
		this.isAdmin = isAdmin;
	}

	boolean isAdmin;

	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "Employee [isAdmin=" + isAdmin + ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword()
				+ "]";
	}
	@Override
	public String getUserType() {
		return "Employee";
	}
}
