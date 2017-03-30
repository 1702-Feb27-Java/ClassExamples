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
	
	public ArrayList<Reimbursement> getPendingReimbursementsForEmployee(Employee e) {
		ArrayList<Reimbursement> reimbursements = null;
		reimbursements = rDao.listAllReimbursements();
		Iterator<Reimbursement> it = reimbursements.iterator();
		while (it.hasNext()) {
		   Reimbursement r = it.next();
		   if (r.getEmployeeId() != e.getEmployeeId())
			   it.remove();
		}
		return reimbursements;
	}
	
	public ArrayList<Reimbursement> getPendingReimbursementsForApprover(Employee approvingEmployee) {
		ArrayList<Reimbursement> reimbursements = null;
		reimbursements = rDao.listAllReimbursements();
		Iterator<Reimbursement> it = reimbursements.iterator();
		while (it.hasNext()) {
		   Reimbursement r = it.next();
		   int pendingEmployeeId = r.getEmployeeId();
		   Employee pendingEmployee = eDao.getEmployee(pendingEmployeeId);

		   // check if not in same dept
		   if(pendingEmployee.getDeptId() != approvingEmployee.getDeptId()){
			   // check if not in benco
			   if (approvingEmployee.getDeptId() != 3)
				   it.remove();
			   // else continue..
		   }		   	   
		   // check if approver not authorized to approve or not an admin
		   if ((approvingEmployee.getRoleId()-2 != r.getAppLevel()) && (approvingEmployee.getRoleId() != 1))
			   it.remove();
		}
		return reimbursements;
	}

}
