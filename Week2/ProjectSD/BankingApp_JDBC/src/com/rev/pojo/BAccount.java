package com.rev.pojo;

public class BAccount {
	int accNo;
	int accType;
	double accBal;
	int userID;
	String task;
	int statusID;
	
	/**
	 * @param accNo
	 * @param accType
	 * @param accBal
	 * @param userID
	 */
	public BAccount(int accNo, int accType, double accBal, int userID,String task, int statusID) {
		super();
		this.accNo = accNo;
		this.accType = accType;
		this.accBal = accBal;
		this.userID = userID;
		this.task=task;
		this.statusID=statusID;
	}

	public BAccount() {
		// TODO Auto-generated constructor stub
	}
	
	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}


	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public int getAccType() {
		return accType;
	}

	public void setAccType(int accType) {
		this.accType = accType;
	}

	public double getAccBal() {
		return accBal;
	}

	public void setAccBal(double accBal) {
		this.accBal = accBal;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String uTask) {
		this.task = uTask;
	}
}
