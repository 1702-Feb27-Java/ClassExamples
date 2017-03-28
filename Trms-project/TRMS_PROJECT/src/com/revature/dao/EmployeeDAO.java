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
	
	//method to get all employees from a database and store them into a data structure
	public ArrayList<Employee> SignInEmployee(String Username);
	//Function to return role number for the said employee. 1 = employee, 2 = direct supervisor, 3 = department head, 4 Benco
	public int getEmployeeRoleNum(String username);
	public int getEmployeeDeptNum(String username);
	
	
}
