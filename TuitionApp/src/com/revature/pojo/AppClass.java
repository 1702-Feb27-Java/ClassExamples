package com.revature.pojo;

public class AppClass {

	private int appID, userID, priority, eventID, cdtID, gradingID, reimburseID;
	private String loc, justification;
	private double totalCost;

	public AppClass(int appID, int userID, int priority, int eventID, int cdtID, String loc, double totalCost,
			int gradingID, String justification, int reimburseID) {
		super();
		this.appID = appID;
		this.userID = userID;
		this.priority = priority;
		this.eventID = eventID;
		this.cdtID = cdtID;
		this.loc = loc;
		this.totalCost = totalCost;
		this.gradingID = gradingID;
		this.justification = justification;
		this.reimburseID = reimburseID;
	}

	public AppClass() {

	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public int getCdtID() {
		return cdtID;
	}

	public void setCdtID(int cdtID) {
		this.cdtID = cdtID;
	}

	public int getGradingID() {
		return gradingID;
	}

	public void setGradingID(int gradingID) {
		this.gradingID = gradingID;
	}

	public int getReimburseID() {
		return reimburseID;
	}

	public void setReimburseID(int reimburseID) {
		this.reimburseID = reimburseID;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "AppClass [appID=" + appID + ", userID=" + userID + ", priority=" + priority + ", eventID=" + eventID
				+ ", cdtID=" + cdtID + ", gradingID=" + gradingID + ", reimburseID=" + reimburseID + ", loc=" + loc
				+ ", justification=" + justification + ", totalCost=" + totalCost + "]";
	}

}
