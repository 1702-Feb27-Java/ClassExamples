package com.revature.dao;

import java.sql.Date;
import java.util.ArrayList;

public interface DAOEmployee {

	public ArrayList<String> loginEmployee(String un, String pw);
	
	public boolean applyForReimbursement(int emp_id, String event, Date eventDate, String time, int location, Date formDate,
			String description, int cost, int gradingId, int typeOfEventId, int urgentId, int approvalStepId, Date approvalCutoff);
	
	public ArrayList<String> getListOfLocations();
	
	public ArrayList<String> getListOfGradingTypes();
	
	public ArrayList<String> getListOfEventTypes();
	
	public int getLocationId(String location);
	
	public int addLocation(String location);
	
	public int getGradingId(String grading);
	
	public int addGrading(String grading, String passingGrade);
	
}
