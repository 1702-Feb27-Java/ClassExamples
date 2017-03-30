package com.revature.pojo;

public class GradingClass {
	
	private int appID, gradingID, gradingFormatID;
	private String gradeCutoff, gradeAwarded, presReview;
	
	public GradingClass(){
		
	}
	
	public GradingClass(int appID, int gradingID, int gradingFormatID, String gradeCutoff,
			String gradeAwarded, String presReview) {
		super();
		this.appID = appID;
		this.gradingID = gradingID;
		this.gradingFormatID = gradingFormatID;
		this.gradeCutoff = gradeCutoff;
		this.gradeAwarded = gradeAwarded;
		this.presReview = presReview;
	}
	
	public int getGradingID() {
		return gradingID;
	}
	public void setGradingID(int gradingID) {
		this.gradingID = gradingID;
	}
	public int getGradingFormatID() {
		return gradingFormatID;
	}
	public void setGradingFormatID(int gradingFormatID) {
		this.gradingFormatID = gradingFormatID;
	}
	public String getGradeCutoff() {
		return gradeCutoff;
	}
	public void setGradeCutoff(String gradeCutoff) {
		this.gradeCutoff = gradeCutoff;
	}
	public String getGradeAwarded() {
		return gradeAwarded;
	}
	public void setGradeAwarded(String gradeAwarded) {
		this.gradeAwarded = gradeAwarded;
	}
	public String getPresReview() {
		return presReview;
	}
	public void setPresReview(String presReview) {
		this.presReview = presReview;
	}
	@Override
	public String toString() {
		return "GradingClass [gradingID=" + gradingID + ", gradingFormatID=" + gradingFormatID
				+ ", GradeCutoff=" + gradeCutoff + ", gradeAwarded=" + gradeAwarded
				+ ", presReview=" + presReview + "]";
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

}
