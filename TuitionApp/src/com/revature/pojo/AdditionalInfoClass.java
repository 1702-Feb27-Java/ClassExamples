package com.revature.pojo;

public class AdditionalInfoClass {
	
	private int additInfoID, appID, resolutionID, requesterID;
	private String requestMessage, infoReturned;
	
	public AdditionalInfoClass(){
		
	}
	
	public AdditionalInfoClass(int additInfoID, int appID, int resolutionID, int requesterID, String requestMessage,
			String infoReturned) {
		super();
		this.additInfoID = additInfoID;
		this.appID = appID;
		this.resolutionID = resolutionID;
		this.requesterID = requesterID;
		this.requestMessage = requestMessage;
		this.infoReturned = infoReturned;
	}
	public int getAdditInfoID() {
		return additInfoID;
	}
	public void setAdditInfoID(int additInfoID) {
		this.additInfoID = additInfoID;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
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
	public String getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	public String getInfoReturned() {
		return infoReturned;
	}
	public void setInfoReturned(String infoReturned) {
		this.infoReturned = infoReturned;
	}

	@Override
	public String toString() {
		return "AdditionalInfoClass [additInfoID=" + additInfoID + ", appID=" + appID + ", resolutionID=" + resolutionID
				+ ", requesterID=" + requesterID + ", requestMessage=" + requestMessage + ", infoReturned="
				+ infoReturned + "]";
	}

}
