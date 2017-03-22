package com.revature.trms;

public class User {
	private int userId =0;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private double amount = 1000.00;
	private int supId=0;
	
	public User(){
		
	}
	
	public User(String firstName, String lastName, String username, String password, 
			String email, int supId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.supId = supId;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", email=" + email + ", amount=" + amount  
				+ ", supId=" + supId + "]";
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getSupId() {
		return supId;
	}
	public void setSupid(int supId) {
		this.supId = supId;
	}
}
