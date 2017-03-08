package com.revature.bankingApp;

public class Customer {

	private String user_name;
	private String password;
	private String AccountName1; //Checking account
	private double Balance1;		//checking balance
	private String AccountName2; //Savings account
	private double Balance2;	 //Savings balance
	
	
	//Constructors
	
	public Customer() {
		user_name = "";
		password = "";
		AccountName1 = "";
		Balance1 = 0.0;
		AccountName2 = "";
		Balance2 = 0.0;
	}
	
	public Customer(String user_name, String password, String AccountName1, double Balance1, String AccountName2, double Balance2 ) {
		this.user_name = user_name;
		this.password = password;
		this.AccountName1 = AccountName1;
		this.Balance1 = Balance1;
		this.AccountName2 = AccountName2;
		this.Balance2 = Balance2;
	}

	//setters and getters
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountName1() {
		return AccountName1;
	}

	public void setAccountName1(String accountName1) {
		AccountName1 = accountName1;
	}

	public double getBalance1() {
		return Balance1;
	}

	public void setBalance1(double balance1) {
		Balance1 = balance1;
	}

	public String getAccountName2() {
		return AccountName2;
	}

	public void setAccountName2(String accountName2) {
		AccountName2 = accountName2;
	}

	public double getBalance2() {
		return Balance2;
	}

	public void setBalance2(double balance2) {
		Balance2 = balance2;
	}

	@Override
	public String toString() {
		return "Customer [user_name=" + user_name + ", password=" + password + ", AccountName1=" + AccountName1
				+ ", Balance1=" + Balance1 + ", AccountName2=" + AccountName2 + ", Balance2=" + Balance2 + "]";
	}

	
	
	
	

}