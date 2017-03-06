package com.revature.BankAccount;

class Customers {
	// customer fields
	protected String firstname;
	protected String lastname;
	protected String username;
	protected String password;
	protected String balance;

	// default constructor
	public Customers() {
	}

	// customer constructor
	public Customers(String fn, String ln, String un, String pw, String bal) {
		this.firstname = fn;
		this.lastname = ln;
		this.username = un;
		this.password = pw;
		this.balance = bal;
	}

	// get customer first name
	public String getFirstname() {
		return firstname;
	}

	// set customer first name
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	// get customer last name
	public String getLastname() {
		return lastname;
	}

	// set customer last name
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	// get customer username
	public String getUsername(String username) {
		return password;
	}

	// set customer username
	public void setUsername(String username) {
		this.password = username;
	}

	// get customer password
	public String getPassword() {
		return password;
	}

	// set customer password
	public void setPassword(String password) {
		this.password = password;
	}

	// get customer balance
	public String getBalance() {
		return balance;
	}

	// set customer balance
	public void setBalance(String balance) {
		this.balance = balance;
	}
}
