package com.revature.dao;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;

import com.revature.connect.ConnectionUtil;
import com.revature.pojo.Employee;



public class EmployeeDAOImpl implements EmployeeDAO {

	//creating ArrayList of employees to add into array upon validation from procedure call
	static public ArrayList<Employee> workers = new ArrayList<Employee>();
	
	@Override
	public ArrayList<Employee> SignInEmployee(String Username, String Password) {
		
		
		//Employee object to use setters upon passing in table information
		Employee emp = new Employee();
		//variables to be passed into employee object then ArrayList
		String Fname, Lname, password;
		int Dept_id, Role_id, Allowance;
		
		//Executing SQL below in try and catch block.
		try( Connection connect = ConnectionUtil.getConnection();) {
			
			//setting auto commit to false
			connect.setAutoCommit(false);
			
			String sql = "CALL EMP_SIGNIN(?, ?, ?, ?, ?, ?, ?)";
			//Using callable statement class to use created procedure
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.setString(1, Username);
			cs.registerOutParameter(2, Types.VARCHAR); //first name
			cs.registerOutParameter(3, Types.VARCHAR); //last name
			cs.registerOutParameter(4, Types.INTEGER); //dept #
			cs.registerOutParameter(5, Types.INTEGER); //role #
			cs.registerOutParameter(6, Types.VARCHAR); //password
			cs.registerOutParameter(7, Types.INTEGER); //money given for reimbursement
			
			cs.execute();
			
			//storing out parameters into employee object but will check that password matches
			Fname = cs.getString(2);
			Lname = cs.getString(3);
			Dept_id = cs.getInt(4);
			Role_id = cs.getInt(5);
			password = cs.getString(6);
			Allowance = cs.getInt(7);
			
			//committing changes
			connect.commit();
			
			//checking if password is what the user passed in
			if ( Password.equals(password) ) {
				System.out.println("User is Valid!");
				emp.setFname(Fname);
				emp.setLname(Lname);
				emp.setDept_id(Dept_id);
				emp.setRole_id(Role_id);
				emp.setUsername(Username);
				emp.setPassword(password);
				emp.setAllowance(Allowance);
				//adding into the array
				workers.add(emp);
			}
			else
				System.out.println("Incorrect Password!");
			

			//print out test
			for ( Employee e: workers)
				System.out.println(e);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean ApplyForReim(String Username, String Location, int add_date, int start_date, int end_date, int course_time, int course_cost,
			 int app_num, int course_id, int grade_type, String grade) {
		//setting boolean to false
		boolean applyed = false;
		
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			/*
			 * 
			 * EMP_ID, LOCATION_, ADD_DATE, START_DATE_COURSE, END_DATE_COURSE, TIME_COURSE, COURSE_COST,
  				APPROVAL_ID, COURSE_ID, GRADE_TYPE_ID, GRADE
			 */
			String SQL = "CALL REIMBURSTMENT_APPLY(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(SQL);
			
			cs.setString(1, Username);
			cs.setString(2, Location);
			cs.setInt(3, add_date);
			cs.setInt(4, start_date);
			cs.setInt(5, end_date);
			cs.setInt(5, course_time);
			cs.setInt(7, course_cost);
			cs.setInt(8, app_num);
			cs.setInt(9, course_id);
			cs.setInt(10, grade_type);
			cs.setString(11, grade);
			
			cs.executeUpdate();
			connect.commit();
			
			//Switching boolean to true;
			applyed = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

}
