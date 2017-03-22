package com.revature.pojo;

public class GradeAttachClass {
	
	private int gradeAttachID, appID, gradeAttachTypeID;
	private String gradeAttachURL;
	
	public GradeAttachClass(){
		
	}
	
	public GradeAttachClass(int gradeAttachID, int appID, int gradeAttachTypeID, String gradeAttachURL) {
		super();
		this.gradeAttachID = gradeAttachID;
		this.appID = appID;
		this.gradeAttachTypeID = gradeAttachTypeID;
		this.gradeAttachURL = gradeAttachURL;
	}

	public int getGradeAttachID() {
		return gradeAttachID;
	}

	public void setGradeAttachID(int gradeAttachID) {
		this.gradeAttachID = gradeAttachID;
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

	public int getGradeAttachTypeID() {
		return gradeAttachTypeID;
	}

	public void setGradeAttachTypeID(int gradeAttachTypeID) {
		this.gradeAttachTypeID = gradeAttachTypeID;
	}

	public String getGradeAttachURL() {
		return gradeAttachURL;
	}

	public void setGradeAttachURL(String gradeAttachURL) {
		this.gradeAttachURL = gradeAttachURL;
	}

	@Override
	public String toString() {
		return "GradeAttachClass [gradeAttachID=" + gradeAttachID + ", appID=" + appID + ", gradeAttachTypeID="
				+ gradeAttachTypeID + ", gradeAttachURL=" + gradeAttachURL + "]";
	}

}
