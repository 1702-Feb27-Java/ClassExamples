package service;

import java.util.ArrayList;
import objects.Employee;
import objects.Reimburse;
import dao.DAOObject;

public class Service {
	private DAOObject db;
	
	public Service(){
		db = new DAOObject();
	}
	
	
	public boolean doesUserNameAndPassMatch(String userName, String pass){
		if(!this.doesUserNameExist(userName)){
			return false;
		}
		Employee temp = db.getEmployee(userName);
		if(temp.getPassword().equals(pass)){
			return true;
		}
		return false;
	}
	
	public boolean doesUserNameExist(String userName){
		ArrayList<Employee> list = db.getAllEmployees();
		
		for(Employee e : list){
			if(e.getUserName().equals(userName)){
				return true;
			}
		}
		return false;
	}
	
	public Employee getEmployee(String userName){
		return db.getEmployee(userName);
	}
	
	public Employee getEmployee(int id)	{
		return db.getEmployee(id);
		
	}
	public void addReimburseRequest(String username, Reimburse req, Employee em, int isUrgent){
		db.addRequest(username, req, em, isUrgent);
	}
	
	public Reimburse getReimburse(int id){
		return db.getReimbursement(id);
		
	}
	
	public void updateStatus(int rID, int newStatus){
		db.updateStatus(rID, newStatus);
	}
	
	public int getStatus(int id){
		return db.getStatus(id);
				
	}
	
	/**
	 * gets if the given value is the department head. Make sure to no envoke on the boss 
	 * @param id the reportsto field of an employee
	 * @return true if the boss is the department head
	 */
	public boolean isBossDepartmentHead(int id){
		return db.isBossDepartmentHead(id);
	}
	
	
	public Employee getDepartmentHead(int id){
		Employee boss = db.getEmployee(id);
		while(boss.getReportsto() != 0){
			boss = db.getEmployee(boss.getReportsto());
		}
		return boss;
	}
	
	public void setApprover(int id, int newApproverId){
		db.updateApprover(id, newApproverId);
	}
	
	public void updatePending(String userName, int newPending){
		db.updatePending(userName, newPending);
	}
	
	public void updateAwarded(String userName, int newPending){
		db.updateAwarded(userName, newPending);
	}
	
	public void addMessage(int re, String mess, int sent){
		db.addMessage(mess, re, sent);
	}
	
	public ArrayList<String> getMessage(int reID){
		return db.getMessage(reID);
	}
	
	public void advanceDay(){
		db.advanceDay();
	}
	
	public ArrayList<Reimburse> getAllReim(){
		return db.getAllReimburse();
	}
	
	public void updateNumDay(int id){
		db.updateNumDay(id);
	}
	

}
