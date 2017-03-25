package com.revature.service;

import java.sql.Date;
import java.util.ArrayList;

import com.revature.dao.DAOEmployeeImpl;
import com.revature.pojo.Message;
import com.revature.pojo.Reimbursement;

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
				employeeId = 0;//password wrong
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

	public int addLocation(String location){
		int locationId = daoEmp.addLocation(location);
		return locationId;
	}
	
	public int getGradingId(String grading){
		int gradingId = daoEmp.getGradingId(grading);
		return gradingId;
	}
	
	public int addGrading(String grading, String passingGrade){
		int gradingId = daoEmp.addGrading(grading, passingGrade);
		return gradingId;
	}

	public int getRoleId(int employeeId){
		int roleId = daoEmp.getRoleId(employeeId);
		return roleId;
	}
	
	public int getTypeOfEventid(String typeOfEvent){
		int typeOfEventId = daoEmp.getTypeOfEventId(typeOfEvent);
		return typeOfEventId;
	}
	
	public int getNumberOfMessages(int employeeId){
		int messages = daoEmp.getNumberOfMessages(employeeId);
		return messages;
	}

	public ArrayList<Message> getMessages(int employeeId){
		ArrayList<Message> messages = new ArrayList<Message>();
		messages = daoEmp.getMessages(employeeId);
		for(Message m : messages){
			m = daoEmp.getMessager(m);
		}
		return messages;
	}

	public ArrayList<Reimbursement> getReimbursements(int employeeId){
		ArrayList<Reimbursement> reimbursements = daoEmp.getReimbursements(employeeId);
		//System.out.println(reimbursements);
		for(Reimbursement r : reimbursements){
			String location = daoEmp.getLocation(r.getLocationId());
			String grading = daoEmp.getGrading(r.getGradingId());
			String typeOfEvent = daoEmp.getTypeOfEvent(r.getTypeOfEventId());
			String approvalStep = daoEmp.getApprovalStep(r.getApprovalStepId());
			
			r.setLocation(location);
			r.setGrading(grading);
			r.setTypeOfEvent(typeOfEvent);
			r.setApprovalStep(approvalStep);
		}
		//System.out.println(reimbursements);
		return reimbursements;
	}

	public Reimbursement getReimbursementById(int reimbId){
		Reimbursement reimbursement = daoEmp.getReimbursementByid(reimbId);
		System.out.println(reimbursement);
		
		String location = daoEmp.getLocation(reimbursement.getLocationId());
		String grading = daoEmp.getGrading(reimbursement.getGradingId());
		String typeOfEvent = daoEmp.getTypeOfEvent(reimbursement.getTypeOfEventId());
		String approvalStep = daoEmp.getApprovalStep(reimbursement.getApprovalStepId());
		
		reimbursement.setLocation(location);
		reimbursement.setGrading(grading);
		reimbursement.setTypeOfEvent(typeOfEvent);
		reimbursement.setApprovalStep(approvalStep);
		
		System.out.println(reimbursement);
		return reimbursement;
	}
}
