package com.revature.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;

import com.revature.dao.DAOEmployeeImpl;

public class EmployeeService {
	
	static DAOEmployeeImpl daoEmp = new DAOEmployeeImpl();
	
	public int loginEmployee(String username, String password){
		int employeeId = 0;//0 = no username found
		int dbEmpId = 0;
		String dbPass = "";
		ArrayList<String> employeeLoggin = new ArrayList<String>();
		employeeLoggin = daoEmp.loginEmployee(username, password);
		if(employeeLoggin != null){
			dbEmpId = Integer.parseInt(employeeLoggin.get(0));
			dbPass = employeeLoggin.get(1);
			if(password.equals(dbPass)){
				employeeId = dbEmpId;//login correct
				return employeeId;
			}
			else{
				employeeId = 1;//password wrong
				return employeeId;
			}
		}
		return employeeId;
	}
	
	public ArrayList<String> getAllLocations(){
		ArrayList<String> locations = new ArrayList<String>();
		
		locations = daoEmp.getListOfLocations();
		
		return locations;
	}
	
	public ArrayList<String> getAllGradingTypes(){
		ArrayList<String> gradingTypes = new ArrayList<String>();
		
		gradingTypes = daoEmp.getListOfGradingTypes();
		
		return gradingTypes;
	}

	public ArrayList<String> getAllTypeOfEvents(){
		ArrayList<String> eventTypes = new ArrayList<String>();
		
		eventTypes = daoEmp.getListOfEventTypes();
		
		return eventTypes;
	}
	
	public boolean applyForReimbursement(int emp_id, String event, Date eventDate, String time, int location, Date formDate,
			String description, int cost, int gradingId, int typeOfEventId, int urgentId, int approvalStepId, Date approvalCutoff){
		boolean applied = false;
		applied = daoEmp.applyForReimbursement(emp_id, event, eventDate, time, location, formDate, description, 
				cost, gradingId, typeOfEventId, urgentId, approvalStepId, approvalCutoff);
		return applied;
	}
	
	public int getLocationId(String location){
		int locationId = daoEmp.getLocationId(location);
		return locationId;
	}
	
	
}