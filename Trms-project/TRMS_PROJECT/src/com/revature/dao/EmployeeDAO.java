package com.revature.dao;
import com.revature.pojo.*;
import java.util.ArrayList;


/**
 * Interface class that will use Employee class, along with the prepared and callable statements
 * to my database
 * @author Nick
 *
 */
public interface EmployeeDAO {
	
	//methods to be declared below
	
	//method to get all employees from a database and store them into a data structure
	//method to apply to the reimbursement form (inserting into the reimbursement table)
	//any further methods will be added upon further evaluation.
	
	public ArrayList<Employee> SignInEmployee(String Username, String Password);
	public boolean ApplyForReim(int e_id, String Location, int start_date, int end_date, int course_time, int course_cost, int reim_amt,
			int reim_id, int app_num, int course_id, int grade_type, String grade);
}
