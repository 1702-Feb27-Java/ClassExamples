package com.revature.dao;

import java.util.ArrayList;

public interface DAOEmployee {

	public ArrayList<String> loginEmployee(String un, String pw);
	
	public boolean applyForReimbursement(String event, int eventDate, int time, String location, int formDate, String description,
			int cost, int grading_id, int typeOfEventId, int urgentId);
	
	public ArrayList<String> getListOfLocations();
}
