package com.revature.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.pojo.Employee;
import com.revature.pojo.Reimbursement;

public class ReimbursementService {
	ReimbursementDaoImpl rDao = new ReimbursementDaoImpl();
	EmployeeDaoImpl eDao = new EmployeeDaoImpl();
	
	// logic for approval, update database accordingly
	public void stage(Reimbursement r) {
		int appLevel = 1;
		Employee applicant = eDao.getEmployee(r.getEmployeeId());
		if(applicant.getRoleId() == 1)
			appLevel = 4;
		else if(applicant.getRoleId() == 3)
			appLevel = 2;
		else if(applicant.getRoleId() == 4)
			appLevel = 3;
		r.setAppLevel(appLevel);
		rDao.save(r);
	}
	
	public void approve(Reimbursement r, Employee approver) {
		int appLevel = 1;
		if(approver.getRoleId() == 1)
			appLevel = 4;
		else if(approver.getRoleId() == 3)
			appLevel = 2;
		else if(approver.getRoleId() == 4)
			appLevel = 3;
		r.setAppLevel(appLevel);
		rDao.save(r);
	}
	
	public void benCoApprove(Reimbursement r) {
		r.setAppLevel(4);
		rDao.save(r);
	}
	
	public void decline(Reimbursement r) {
		r.setAppLevel(5);
		rDao.save(r);
	}
	
	public void requestInfo(Reimbursement r) {
		r.setAppLevel(6);
		rDao.save(r);
	}
	
	// getting reimbursements
	public ArrayList<Reimbursement> getReimbursementsByEmployeeId(int eId) {
		ArrayList<Reimbursement> reimbursements = null;
		reimbursements = rDao.listReimbursementsByEmployee(eId);
		return reimbursements;
	}
	
	public ArrayList<Reimbursement> getAllReimbursements() {
		ArrayList<Reimbursement> reimbursements = null;
		reimbursements = rDao.listAllReimbursements();
		return reimbursements;
	}
	
	public ArrayList<Reimbursement> getPendingReimbursementsByEmployee(Employee e) {
		ArrayList<Reimbursement> reimbursements = null;
		reimbursements = rDao.listReimbursementsByEmployee(e.getEmployeeId());
		Iterator<Reimbursement> it = reimbursements.iterator();
		while (it.hasNext()) {
		   Reimbursement r = it.next();
		   if (r.getAppLevel() >= 4) {
			   it.remove();
		   }
		}
		return reimbursements;
	}
	

	public ArrayList<Reimbursement> getMatriculatedReimbursementsByEmployee(Employee e) {
		ArrayList<Reimbursement> reimbursements = null;
		reimbursements = rDao.listReimbursementsByEmployee(e.getEmployeeId());
		Iterator<Reimbursement> it = reimbursements.iterator();
		while (it.hasNext()) {
		   Reimbursement r = it.next();
		   if (r.getAppLevel() < 4) {
			   it.remove();
		   }
		}
		return reimbursements;
	}

	
	public ArrayList<Reimbursement> getApprovableReimbursements(Employee approvingEmployee) {
		ArrayList<Reimbursement> reimbursements = null;
		reimbursements = rDao.listAllReimbursements();
		Iterator<Reimbursement> it = reimbursements.iterator();
		while (it.hasNext()) {
		   Reimbursement r = it.next();
		   int pendingEmployeeId = r.getEmployeeId();
		   Employee pendingEmployee = eDao.getEmployee(pendingEmployeeId);
		   if (pendingEmployee.getEmployeeId() == approvingEmployee.getEmployeeId())
			   it.remove();
		   // check if not in same dept
		   else if(pendingEmployee.getDeptId() != approvingEmployee.getDeptId())
			   it.remove();	   	   
		   // check if approver not authorized to approve or not an admin
		   else if ((approvingEmployee.getRoleId()-2 != r.getAppLevel()) && (approvingEmployee.getRoleId() != 1))
			   it.remove();
		}
		return reimbursements;
	}
	
	public ArrayList<Reimbursement> getFinalizableReimbursements(Employee approvingEmployee) {
		ArrayList<Reimbursement> reimbursements = null;
		reimbursements = rDao.listAllReimbursements();
		Iterator<Reimbursement> it = reimbursements.iterator();
		while (it.hasNext()) {
		   Reimbursement r = it.next();
		   int pendingEmployeeId = r.getEmployeeId();
		   Employee pendingEmployee = eDao.getEmployee(pendingEmployeeId);
		   // check if not in last approval step
		   if (r.getAppLevel() != 3)
			   it.remove();
		   // check if not in benco
		   else if (approvingEmployee.getDeptId() != 4)
			   it.remove();
		   // check if both in benco
		   else if(pendingEmployee.getDeptId() == approvingEmployee.getDeptId()){
			   if (pendingEmployee.getEmployeeId() == approvingEmployee.getEmployeeId())
				   it.remove();
		   }
		}
		return reimbursements;
	}
	
	/**
	 * Inserts reimbursement using reimbursement dao
	 * @param r reimbursement to be inserted
	 * @return reimbursement int id
	 */
	public int insertReimbursement(Reimbursement r){
		rDao.create(r);
		return r.getReimbId(); 
	}

	public Reimbursement getReimbursementsByReimbursementId(int rId) {
		// TODO Auto-generated method stub
		Reimbursement r = rDao.getReimbursement(rId);
		return r;
	}
	

}
