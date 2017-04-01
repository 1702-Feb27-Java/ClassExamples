package com.revature.pojo;

public class GradeAttachClass {
	
	private int gradeAttachID, appID;
	private String gradeAttachURL;
	
	public GradeAttachClass(){
		
	}
	
	public GradeAttachClass(int gradeAttachID, int appID, String gradeAttachURL) {
		super();
		this.gradeAttachID = gradeAttachID;
		this.appID = appID;
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


	public String getGradeAttachURL() {
		return gradeAttachURL;
	}

	public void setGradeAttachURL(String gradeAttachURL) {
		this.gradeAttachURL = gradeAttachURL;
	}

	@Override
	public String toString() {
		return "GradeAttachClass [gradeAttachID=" + gradeAttachID + ", appID=" + appID + ", gradeAttachURL=" + gradeAttachURL + "]";
	}

}
