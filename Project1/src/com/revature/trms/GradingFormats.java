package com.revature.trms;

public class GradingFormats {

	private int gradeFormatId;
	private String gradeFormat;
	
	public GradingFormats(){
		
	}
	
	public GradingFormats(int gradeFormatId) {
		super();
		this.gradeFormatId = gradeFormatId;
	}
	
	public int getGradeFormatId() {
		return gradeFormatId;
	}
	
	public void setGradeFormatId(int gradeFormatId) {
		this.gradeFormatId = gradeFormatId;
	}
	
	public String getGradeFormat() {
		return gradeFormat;
	}
	
	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}
}
