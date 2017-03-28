package com.revature.service;

import java.sql.Date;
import java.util.ArrayList;

import com.revature.dao.DAOEmployeeImpl;
import com.revature.pojo.Message;
import com.revature.pojo.Reimbursement;

import jdk.nashorn.internal.runtime.RecompilableScriptFunctionData;

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

		return reimbursements;
	}

	public Reimbursement getReimbursementById(int reimbId){
		Reimbursement reimbursement = daoEmp.getReimbursementByid(reimbId);
		
		String location = daoEmp.getLocation(reimbursement.getLocationId());
		String grading = daoEmp.getGrading(reimbursement.getGradingId());
		String typeOfEvent = daoEmp.getTypeOfEvent(reimbursement.getTypeOfEventId());
		String approvalStep = daoEmp.getApprovalStep(reimbursement.getApprovalStepId());
		
		reimbursement.setLocation(location);
		reimbursement.setGrading(grading);
		reimbursement.setTypeOfEvent(typeOfEvent);
		reimbursement.setApprovalStep(approvalStep);
		
		return reimbursement;
	}

	public boolean submitEdit(int reimbId, ArrayList<String> attachmentLinks){
		boolean link = false;
		for(String s : attachmentLinks){
			link = daoEmp.submitEdit(reimbId, s);
		}
		return link;
	}

	public ArrayList<Reimbursement> getReimbursements(int approvalStepId, int departmentId){
		ArrayList<Reimbursement> tempReimbursements = daoEmp.getPendingReimbursementsByApprovalStep(approvalStepId);
		ArrayList<Integer> employees = daoEmp.getEmployeesByDepartment(departmentId);
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		for(Reimbursement r : tempReimbursements){
			for(Integer empId : employees){
				if(r.getEmpId() == empId)
					reimbursements.add(r);
			}
		}
		
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
		
		return reimbursements;
	}

	public int getDepartment(int empId){
		int department = daoEmp.getDepartment(empId);
		return department;		
	}

	public ArrayList<Reimbursement> getReimbursementsByApprovalStep(int approvalStepId){
		ArrayList<Reimbursement> reimbursements = daoEmp.getPendingReimbursementsByApprovalStep(approvalStepId);
		
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
		
		return reimbursements;
	}
	
	public boolean updateReimbursement(int reimbId, int empId, int roleId, int deptId, boolean approve){
		boolean result = daoEmp.updateReimbursement(reimbId, empId, roleId, deptId, approve);
		return result;
	}
	
}
