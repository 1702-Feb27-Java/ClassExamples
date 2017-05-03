package JDBC;

import java.util.LinkedList;
import Classes.Employee;
import Classes.Reimbursement;

public interface ReimbursementDAO
{
	////// CREATE
	public boolean insertReimbursement(Reimbursement per);

	///// READ
	public LinkedList<Reimbursement> getAllReimbursement();

	public LinkedList<Reimbursement> getReimbursementsByApproval_ID(int id);
	
	public LinkedList<Reimbursement> getReimbursementsByEmployee_ID(int id);

	public Reimbursement getReimbursementByReimbursement_Id(int id);

	////// UPDATE
	public void saveReimbursement(Reimbursement per);

	////// DELETE
	public void deleteReimbursementById(int id);

}
