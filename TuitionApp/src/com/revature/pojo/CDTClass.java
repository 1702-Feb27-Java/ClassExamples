package com.revature.pojo;

public class CDTClass {
	
	private String startdate;
	private String enddate;
	private int hoursPerWeek;
	
	public CDTClass(){
		
	}
	
	public CDTClass(String startdate, String enddate, int hoursPerWeek) {
		super();
		this.startdate = startdate;
		this.enddate = enddate;
		this.hoursPerWeek = hoursPerWeek;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public int getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(int hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	@Override
	public String toString() {
		return "CDTClass [startdate=" + startdate + ", enddate=" + enddate + ", hoursPerWeek=" + hoursPerWeek + "]";
	}

}
