package com.revature.pojo;

public class NotifClass {
	
	private int notifID, userID, resolutionID, requesterID;
	private String notifMessage;
	
	public NotifClass(){
		
	}
	
	public NotifClass(int notifID, int userID, int resolutionID, int requesterID, String notifMessage) {
		super();
		this.notifID = notifID;
		this.userID = userID;
		this.resolutionID = resolutionID;
		this.requesterID = requesterID;
		this.notifMessage = notifMessage;
	}

	public int getNotifID() {
		return notifID;
	}

	public void setNotifID(int notifID) {
		this.notifID = notifID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getResolutionID() {
		return resolutionID;
	}

	public void setResolutionID(int resolutionID) {
		this.resolutionID = resolutionID;
	}

	public int getRequesterID() {
		return requesterID;
	}

	public void setRequesterID(int requesterID) {
		this.requesterID = requesterID;
	}

	public String getNotifMessage() {
		return notifMessage;
	}

	public void setNotifMessage(String notifMessage) {
		this.notifMessage = notifMessage;
	}

	@Override
	public String toString() {
		return "NotifClass [notifID=" + notifID + ", userID=" + userID + ", resolutionID=" + resolutionID
				+ ", requesterID=" + requesterID + ", notifMessage=" + notifMessage + "]";
	}

}
