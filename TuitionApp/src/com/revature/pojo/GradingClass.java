package com.revature.pojo;

public class GradingClass {
	
	private int appID, gradingID, gradingFormatID;
	private String gradeCutoff, defaultGrade, gradeAwarded, presReview;
	
	public GradingClass(){
		
	}
	
	public GradingClass(int appID, int gradingID, int gradingFormatID, String gradeCutoff, String defaultGrade,
			String gradeAwarded, String presReview) {
		super();
		this.appID = appID;
		this.gradingID = gradingID;
		this.gradingFormatID = gradingFormatID;
		this.gradeCutoff = gradeCutoff;
		this.defaultGrade = defaultGrade;
		this.gradeAwarded = gradeAwarded;
		this.presReview = presReview;
	}
	
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
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
	public String getDefaultGrade() {
		return defaultGrade;
	}
	public void setDefaultGrade(String defaultGrade) {
		this.defaultGrade = defaultGrade;
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
		return "GradingClass [appID=" + appID + ", gradingID=" + gradingID + ", gradingFormatID=" + gradingFormatID
				+ ", GradeCutoff=" + gradeCutoff + ", DefaultGrade=" + defaultGrade + ", gradeAwarded=" + gradeAwarded
				+ ", presReview=" + presReview + "]";
	}

}
