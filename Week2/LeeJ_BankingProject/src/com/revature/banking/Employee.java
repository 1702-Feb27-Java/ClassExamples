package com.revature.banking;

import java.util.ArrayList;

public class Employee extends User{
	private int id = 0;
	private String username = null;
	private String password = null;
	private ArrayList<Integer> customerIds = new ArrayList<>();
	
	public Employee(int i, String un, String pw) {
		// TODO if customer id already used, increment id until not found
		// write customer info to txt file
		this.id = i;
		this.username = un;
		this.password = pw;
	}
	

	public void addCustomerById(int id) {
		// reference id-indexed account file
		// TODO Auto-generated method stub
		this.customerIds.add(id);

	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	public ArrayList<Integer> getCustomerIds() {
		return customerIds;
	}


	public void setCustomerIds(ArrayList<Integer> customerIds) {
		this.customerIds = customerIds;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return (id + ":" + username + ":" + password + ":" + listToString(customerIds));
	}

	// turns ArrayList type var customerIds into a formatted string
	public static String listToString(ArrayList<Integer> cIds) {
		String returnStrArr[] = new String[cIds.size()];
		for (int i = 0; i < cIds.size(); i++) {
			returnStrArr[i] = cIds.get(i).toString();
		}
		return String.join(":", returnStrArr);
	}
}
