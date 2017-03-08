package com.revature.banking;


// class for creating a customer object

public class CustomerClass {

	// private members of a customer class
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String id;
	private double checkingAmount;
	private double savingsAmount;

	// getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public double getCheckingAmount() {
		return checkingAmount;
	}

	public void setCheckingAmount(double checkingAmount) {
		this.checkingAmount = checkingAmount;
	}

	public double getSavingsAmount() {
		return savingsAmount;
	}

	public void setSavingsAmount(double savingsAmount) {
		this.savingsAmount = savingsAmount;
	}

	// override toString
	public String toString() {
		return "Customer:" + firstName + ":" + lastName + ":" + username + ":" + password + ":";

	}

}
