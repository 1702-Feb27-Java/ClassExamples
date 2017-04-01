package com.revature.pojo;

public class Grade {
	private int gradeId;
	private String gradeFormat;
	private String passingGrade;
	private boolean presReq;
	
	public Grade(){
		
	}
	
	public Grade(String gradeFormat, String passingGrade, boolean presReq) {
		this.gradeFormat = gradeFormat;
		this.passingGrade = passingGrade;
		this.presReq = presReq;
	}
	
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeFormat() {
		return gradeFormat;
	}
	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}
	public String getPassingGrade() {
		return passingGrade;
	}
	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}
	public boolean isPresReq() {
		return presReq;
	}
	public void setPresReq(boolean presReq) {
		this.presReq = presReq;
	}
	
}
