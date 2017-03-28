package com.revature.dao;

import java.sql.Date;
import java.util.ArrayList;

import com.revature.pojo.Message;
import com.revature.pojo.Reimbursement;

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
	
	public int getRoleId(int employeeId);
	
	public int getTypeOfEventId(String typeOfEvent);
	
	public int getNumberOfMessages(int employeeId);
	
	public ArrayList<Message> getMessages(int employeeId);
	
	public Message getMessager(Message msg);
	
	public ArrayList<Reimbursement> getReimbursements(int employeeId);
	
	public String getLocation(int locationId);
	
	public String getGrading(int gradingId);
	
	public String getTypeOfEvent(int typeOfEventId);
	
	public String getApprovalStep(int approvalId);
	
	public Reimbursement getReimbursementByid(int reimbId);

	public boolean submitEdit(int reimbId, String attachment);

	public ArrayList<Reimbursement> getPendingReimbursementsByApprovalStep(int approvalStepId);
	
	public ArrayList<Integer> getEmployeesByDepartment(int departmentId);
	
	public int getDepartment(int empId);

	public boolean updateReimbursement(int reimbId,int empId, int roleId, int deptId, boolean approve);
	
	
}
