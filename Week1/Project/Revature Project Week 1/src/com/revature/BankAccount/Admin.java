package com.revature.BankAccount;

class Admin extends Employees {
	// admin fields
	protected String id;
	protected String firstname;
	protected String lastname;
	protected String username;
	protected String password;

	
	// default constructor
		public Admin() {
		}

		// customer constructor
		public Admin(String fn, String ln, String un, String pw) {
			this.firstname = fn;
			this.lastname = ln;
			this.username = un;
			this.password = pw;
		}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
