package com.revature.trms;

public class Status {

	private int statusId = 0;
	private String status;
	
	public Status() {
		
	}
	
	public Status(int statusId) {
		this.statusId = statusId;		
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
