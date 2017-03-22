package com.revature.pojo;

public class AttachmentClass {
	
	private int attachID, appID, attachTypeID;
	private String attachURL;
	
	public AttachmentClass(){
		
	}
	
	public AttachmentClass(int attachID, int appID, int attachTypeID, String attachURL) {
		super();
		this.attachID = attachID;
		this.appID = appID;
		this.attachTypeID = attachTypeID;
		this.attachURL = attachURL;
	}
	public int getAttachID() {
		return attachID;
	}
	public void setAttachID(int attachID) {
		this.attachID = attachID;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public int getAttachTypeID() {
		return attachTypeID;
	}
	public void setAttachTypeID(int attachTypeID) {
		this.attachTypeID = attachTypeID;
	}
	public String getAttachURL() {
		return attachURL;
	}
	public void setAttachURL(String attachURL) {
		this.attachURL = attachURL;
	}

	@Override
	public String toString() {
		return "AttachmentClass [attachID=" + attachID + ", appID=" + appID + ", attachTypeID=" + attachTypeID
				+ ", attachURL=" + attachURL + "]";
	}

}
