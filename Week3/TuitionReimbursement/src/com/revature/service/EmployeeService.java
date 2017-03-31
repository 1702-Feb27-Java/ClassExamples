package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.revature.dao.DAOEmployeeImpl;
import com.revature.pojo.Message;
import com.revature.pojo.Reimbursement;
import com.revature.util.ConnectionUtil;

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
	
	public int applyForReimbursement(int emp_id, String event, Date eventDate, String time, int location, Date formDate,
			String description, int cost, int gradingId, int typeOfEventId, int urgentId, int approvalStepId, Date approvalCutoff){
		int reimbId = 0;
		
		java.sql.Date eventDate2 = new java.sql.Date(eventDate.getTime());
		java.sql.Date formDate2 = new java.sql.Date(formDate.getTime());
		java.sql.Date approvalCutoff2 = new java.sql.Date(approvalCutoff.getTime());
		
		reimbId = daoEmp.applyForReimbursement(emp_id, event, eventDate2, time, location, formDate2, description, 
				cost, gradingId, typeOfEventId, urgentId, approvalStepId, approvalCutoff2);
		return reimbId;
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
			
			int typeOfEventId = r.getTypeOfEventId();
			double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
			double projectedReimbursement = percentAwarded * r.getCost();
			
			r.setpProjectedReimbursement(projectedReimbursement);
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
		
		int typeOfEventId = reimbursement.getTypeOfEventId();
		double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
		double projectedReimbursement = percentAwarded * reimbursement.getCost();
		
		reimbursement.setpProjectedReimbursement(projectedReimbursement);
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

	public ArrayList<Reimbursement> getReimbursements(int approvalStepId, int departmentId, int employeeId){
		ArrayList<Reimbursement> tempReimbursements = daoEmp.getPendingReimbursementsByApprovalStep(approvalStepId, employeeId);
		if(departmentId == 0){
			for(Reimbursement r : tempReimbursements){
				String location = daoEmp.getLocation(r.getLocationId());
				String grading = daoEmp.getGrading(r.getGradingId());
				String typeOfEvent = daoEmp.getTypeOfEvent(r.getTypeOfEventId());
				String approvalStep = daoEmp.getApprovalStep(r.getApprovalStepId());
				
				int typeOfEventId = r.getTypeOfEventId();
				double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
				double projectedReimbursement = percentAwarded * r.getCost();
				
				r.setpProjectedReimbursement(projectedReimbursement);
				r.setLocation(location);
				r.setGrading(grading);
				r.setTypeOfEvent(typeOfEvent);
				r.setApprovalStep(approvalStep);
			}
			return tempReimbursements;
		}
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
			
			int typeOfEventId = r.getTypeOfEventId();
			double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
			double projectedReimbursement = percentAwarded * r.getCost();
			
			r.setpProjectedReimbursement(projectedReimbursement);
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

	public ArrayList<Reimbursement> getReimbursementsByApprovalStep(int approvalStepId, int employeeId){
		ArrayList<Reimbursement> reimbursements = daoEmp.getPendingReimbursementsByApprovalStep(approvalStepId, employeeId);
		
		for(Reimbursement r : reimbursements){
			String location = daoEmp.getLocation(r.getLocationId());
			String grading = daoEmp.getGrading(r.getGradingId());
			String typeOfEvent = daoEmp.getTypeOfEvent(r.getTypeOfEventId());
			String approvalStep = daoEmp.getApprovalStep(r.getApprovalStepId());
			
			int typeOfEventId = r.getTypeOfEventId();
			double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
			double projectedReimbursement = percentAwarded * r.getCost();
			
			r.setpProjectedReimbursement(projectedReimbursement);
			r.setLocation(location);
			r.setGrading(grading);
			r.setTypeOfEvent(typeOfEvent);
			r.setApprovalStep(approvalStep);
		}
		
		return reimbursements;
	}
	
	public boolean updateReimbursement(int reimbId, int empId, int roleId, int deptId, boolean approve, String reason){
		boolean result = daoEmp.updateReimbursement(reimbId, empId, roleId, deptId, approve, reason);
		return result;
	}
	
	public int getEmployeeIdByReimbursementId(int reimbId){
		int empId = daoEmp.getEmployeeIdByReimbursementId(reimbId);
		return empId;
	}
	
	public String getEmployeeName(int empId){
		String employee = daoEmp.getEmployeeName(empId);
		return employee;
	}

	public boolean addMessage(String message, int empId, int messagerId, int reimbId){
		boolean result = daoEmp.addMessage(message, empId, messagerId, reimbId);
		return result;
	}
	
	public int getBalance(int empId){
		int balance = daoEmp.getBalance(empId);
		return balance;
	}
	
	public ArrayList<Reimbursement> getApprovedReimbursements(int employeeId){
		ArrayList<Reimbursement> approvedReimbursements = new ArrayList<Reimbursement>();
		ArrayList<Reimbursement> reimbursements = daoEmp.getReimbursements(employeeId);
		
		for(Reimbursement r : reimbursements){
			if(r.getApprovalStepId() == 4){
				approvedReimbursements.add(r);
			}
		}
		for(Reimbursement r : approvedReimbursements){
			String location = daoEmp.getLocation(r.getLocationId());
			String grading = daoEmp.getGrading(r.getGradingId());
			String typeOfEvent = daoEmp.getTypeOfEvent(r.getTypeOfEventId());
			String approvalStep = daoEmp.getApprovalStep(r.getApprovalStepId());
			
			int typeOfEventId = r.getTypeOfEventId();
			double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
			double projectedReimbursement = percentAwarded * r.getCost();
			
			r.setpProjectedReimbursement(projectedReimbursement);
			r.setLocation(location);
			r.setGrading(grading);
			r.setTypeOfEvent(typeOfEvent);
			r.setApprovalStep(approvalStep);
		}
		
		return approvedReimbursements;
	}
	
	public void submitGrade(int reimbId, String grade){
		daoEmp.submitGrade(reimbId, grade);
	}
	
	public ArrayList<Reimbursement> getDeclinedReimbursements(int employeeId){
		ArrayList<Reimbursement> declinedReimbursements = new ArrayList<Reimbursement>();
		ArrayList<Reimbursement> reimbursements = daoEmp.getReimbursements(employeeId);
		
		for(Reimbursement r : reimbursements){
			if(r.getApprovalStepId() == 6){
				declinedReimbursements.add(r);
			}
		}
		for(Reimbursement r : declinedReimbursements){
			String location = daoEmp.getLocation(r.getLocationId());
			String grading = daoEmp.getGrading(r.getGradingId());
			String typeOfEvent = daoEmp.getTypeOfEvent(r.getTypeOfEventId());
			String approvalStep = daoEmp.getApprovalStep(r.getApprovalStepId());
			
			int typeOfEventId = r.getTypeOfEventId();
			double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
			double projectedReimbursement = percentAwarded * r.getCost();
			
			r.setpProjectedReimbursement(projectedReimbursement);
			r.setLocation(location);
			r.setGrading(grading);
			r.setTypeOfEvent(typeOfEvent);
			r.setApprovalStep(approvalStep);
		}
		
		return declinedReimbursements;
	}
	
	public void markMessageRead(int messageId){
		daoEmp.markMessageRead(messageId);
	}
	
	public ArrayList<Reimbursement> getPaidReimbursements(int employeeId){
		ArrayList<Reimbursement> paidReimbursements = new ArrayList<Reimbursement>();
		ArrayList<Reimbursement> reimbursements = daoEmp.getReimbursements(employeeId);
		
		for(Reimbursement r : reimbursements){
			if(r.getApprovalStepId() == 7){
				paidReimbursements.add(r);
			}
		}
		for(Reimbursement r : paidReimbursements){
			String location = daoEmp.getLocation(r.getLocationId());
			String grading = daoEmp.getGrading(r.getGradingId());
			String typeOfEvent = daoEmp.getTypeOfEvent(r.getTypeOfEventId());
			String approvalStep = daoEmp.getApprovalStep(r.getApprovalStepId());
			
			int typeOfEventId = r.getTypeOfEventId();
			double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
			double projectedReimbursement = percentAwarded * r.getCost();
			
			r.setpProjectedReimbursement(projectedReimbursement);
			r.setLocation(location);
			r.setGrading(grading);
			r.setTypeOfEvent(typeOfEvent);
			r.setApprovalStep(approvalStep);
		}
		
		return paidReimbursements;
	}
	
	public void finalUpdate(int reimbId, boolean approve){
		daoEmp.finalUpdate(reimbId, approve);
	}
	
	public ArrayList<Reimbursement> getPendingReimbursements(int employeeId){
			ArrayList<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
			ArrayList<Reimbursement> reimbursements = daoEmp.getReimbursements(employeeId);

			for(Reimbursement r : reimbursements){
				String location = daoEmp.getLocation(r.getLocationId());
				String grading = daoEmp.getGrading(r.getGradingId());
				String typeOfEvent = daoEmp.getTypeOfEvent(r.getTypeOfEventId());
				String approvalStep = daoEmp.getApprovalStep(r.getApprovalStepId());
				
				int typeOfEventId = r.getTypeOfEventId();
				double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
				double projectedReimbursement = percentAwarded * r.getCost();
				
				r.setpProjectedReimbursement(projectedReimbursement);
				r.setLocation(location);
				r.setGrading(grading);
				r.setTypeOfEvent(typeOfEvent);
				r.setApprovalStep(approvalStep);
			}
			
			for(Reimbursement r : reimbursements){
				if(r.getApprovalStepId() == 1 || r.getApprovalStepId() == 2 || r.getApprovalStepId() == 3){
					pendingReimbursements.add(r);
				}
			}
			
			return pendingReimbursements;
	}
	
	public double getPercentAwarded(int typeOfEventId){
		double percentAwarded = daoEmp.getPercentAwarded(typeOfEventId);
		return percentAwarded;
	}

	public boolean sendMoney(int empId, double amount){
		boolean success = false;
		
		double balance = daoEmp.getBalance(empId);
		balance -= amount;
		success = daoEmp.setBalance(empId, balance);
		
		return success;
	}
	
	public ArrayList<String> getAttachmentsByReimbursementId(int reimbId){
		ArrayList<String> attachments = daoEmp.getAttachmentsByReimbursementId(reimbId);
		return attachments;
	}
	
	public void markFinalApprover(int reimbId, int approver){
		daoEmp.markFinalApprover(reimbId, approver);
	}
	
	public void checkAutoApprove(Date todaysDate){
		ArrayList<Reimbursement> reimbursements = daoEmp.getAllReimbursements();
		//System.out.println(reimbursements);
		for(Reimbursement r : reimbursements){
			if(r.getApprovalStepId() == 1 || r.getApprovalStepId() == 2){
				
				Date cutoffDate =  (Date)r.getCutoffDate();
				int daysApart = (int)((cutoffDate.getTime() - todaysDate.getTime()) / (1000*60*60*24l));
				if(daysApart < 0){
					
					//add 7 days to current day to get cutoff day
					Calendar c = Calendar.getInstance();
					c.setTime(todaysDate);
					c.add(Calendar.DATE, 7);  // number of days to add
					Date cutoff = c.getTime();
					java.sql.Date cutoffDate2 = new java.sql.Date(cutoff.getTime());

					daoEmp.autoApprove(r.getReimbId(), r.getApprovalStepId() + 1);
					daoEmp.addMessage("Auto Approved", r.getEmpId(), 16, r.getReimbId());
					daoEmp.updateCutoffDate(r.getReimbId(), cutoffDate2);
						
				}
			}
		}
	}
}





