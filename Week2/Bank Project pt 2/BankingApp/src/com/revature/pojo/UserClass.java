package com.revature.pojo;

// this creates a class that stores all the user info
public class UserClass {

	// fields
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private int userID;
	private int roleID;

	// constructors
	public UserClass(String firstName, String lastName, String username, String password, int userID, int roleID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.userID = userID;
		this.roleID = roleID;
	}

	public UserClass() {

	}

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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	@Override
	public String toString() {
		return "UserClass [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", userID=" + userID + ", roleID=" + roleID + "]";
	}

	
}
