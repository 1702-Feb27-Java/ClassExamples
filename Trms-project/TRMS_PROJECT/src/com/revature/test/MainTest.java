package com.revature.test;
import com.revature.dao.*;
import com.revature.connect.*;

/**
 * Main class to test out DAO methods to see if they work.
 * @author Nick
 *
 */
public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String user_name = "nperez";
		String pass_word = "admin222";
		
		EmployeeDAOImpl this_emp = new EmployeeDAOImpl();
		
		this_emp.SignInEmployee(user_name, pass_word);

	}

}
