package com.revature.dao;

import java.sql.Date;
import java.util.ArrayList;

public interface DAOEmployee {

	public ArrayList<String> loginEmployee(String un, String pw);
	
	public boolean applyForReimbursement(int emp_id, String event, Date eventDate, String time, int location, Date formDate,
			String description, int cost, int gradingId, int typeOfEventId, int urgentId, int approvalStepId, int approvalCutoff);
	
	public ArrayList<String> getListOfLocations();
	
	public ArrayList<String> getListOfGradingTypes();
	
	public ArrayList<String> getListOfEventTypes();
	
}
