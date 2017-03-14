package com.revature.banking;

import java.util.ArrayList;

public class Customer extends User{
	private int id = 0;
	private String firstname = null;
	private String lastname = null;
	private String username = null;
	private String password = null;
	private ArrayList<Integer> accountIds = new ArrayList<>();
	
	public Customer(String fName, String lName, String un, String pw) {
		// TODO if customer id already used, increment id until not found
		// write customer info to txt file
		this.setFirstname(fName);
		this.setLastname(lName);
		this.username = un;
		this.password = pw;
	}


	public Customer() {
		// TODO Auto-generated constructor stub
	}


	public void addAccountById(int id) {
		// reference id-indexed account file
		// TODO Auto-generated method stub
		this.accountIds.add(id);

	}
	
	public ArrayList<Integer> getAccountIds(){
		return this.accountIds;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return (id + ":" + username + ":" + password + ":" + listToString(accountIds));
	}
	
	// turns ArrayList type var accountIds into a formatted string
	public static String listToString(ArrayList<Integer> aIds){
		String returnStrArr[] = new String[aIds.size()];
		for(int i = 0; i < aIds.size(); i++){
			returnStrArr[i] = aIds.get(i).toString();
		}
		return String.join(":", returnStrArr);
	}
	

	public String prettyPrint() {
		return "Username: " + username + "\nCustomer ID #" + id;
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
	
}
