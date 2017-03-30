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
	public ArrayList<Employee> SignInEmployee(String Username) {
		
		
		//Employee object to use setters upon passing in table information
		Employee emp = new Employee();
		//variables to be passed into employee object then ArrayList
		String Fname, Lname, password;
		int Dept_id, Emp_id, Role_id, Allowance;
		
		//Executing SQL below in try and catch block.
		try( Connection connect = ConnectionUtil.getConnection();) {
			//System.out.println("TEST");
			//setting auto commit to false
			connect.setAutoCommit(false);
			
			String sql = "CALL EMP_SIGNIN(?, ?, ?, ?, ?, ?, ?, ?)";
			//Using callable statement class to use created procedure
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.setString(1, Username);
			cs.registerOutParameter(2, Types.VARCHAR); //first name
			cs.registerOutParameter(3, Types.VARCHAR); //last name
			cs.registerOutParameter(4, Types.INTEGER); //dept #
			cs.registerOutParameter(5, Types.INTEGER); //emp #
			cs.registerOutParameter(6, Types.INTEGER); //role #
			cs.registerOutParameter(7, Types.VARCHAR); //password
			cs.registerOutParameter(8, Types.INTEGER); //money given for reimbursement
			
			cs.execute();
			
			//storing out parameters into employee object but will check that password matches
			Fname = cs.getString(2);
			Lname = cs.getString(3);
			Dept_id = cs.getInt(4);
			Emp_id = cs.getShort(5);
			Role_id = cs.getInt(6);
			password = cs.getString(7);
			Allowance = cs.getInt(8);
			
			//committing changes
			connect.commit();
			
			//checking if password is what the user passed in
			emp.setFname(Fname);
			emp.setLname(Lname);
			emp.setDept_id(Dept_id);
			emp.setRole_id(Role_id);
			emp.setEmp_id(Emp_id);
			emp.setUsername(Username);
			emp.setPassword(password);
			emp.setAllowance(Allowance);
				
			workers.add(emp);
			
				
			//print out test
			for ( Employee e: workers)
				System.out.println(e);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return workers;
	}

	@Override
	public int getEmployeeRoleNum(String username) {
		int role_id = 0;
		
		try( Connection connect = ConnectionUtil.getConnection();) {
			
			
			connect.setAutoCommit(false);
			String sql = "SELECT ROLE_ID FROM EMPLOYEE WHERE USERNAME = '" + username + "'";
			
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				role_id = rs.getInt("ROLE_ID");
			}
			rs.close();
			
			
			connect.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(role_id);
		return role_id;
		
	}

	@Override
	public int getEmployeeDeptNum(String username) {
		int Dept_id = 0;
		
		try( Connection connect = ConnectionUtil.getConnection();) {
			
			connect.setAutoCommit(false);
			String sql = "SELECT DEPT_ID FROM EMPLOYEE WHERE USERNAME = ?";
			
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, username);
			Dept_id = statement.executeUpdate();
			
			connect.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(Dept_id);
		return Dept_id;
		
	}

	@Override
	public Employee getUser(String n) {
		
		Employee e = null;
		
		try( Connection connect = ConnectionUtil.getConnection();) {
			
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?";
			
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, n);
			ResultSet rs = statement.executeQuery();
			
			while( rs.next() ){
				e = new Employee();
				e.setFname(rs.getString("FNAME"));
				e.setLname(rs.getString("LNAME"));
				e.setDept_id(rs.getInt("DEPT_ID"));
				e.setRole_id(rs.getInt("ROLE_ID"));
				e.setEmp_id(rs.getInt("EMP_ID"));
				e.setUsername(rs.getString("USERNAME"));
				e.setPassword(rs.getString("PASSWORD"));
				e.setAllowance(rs.getInt("ALLOWANCE"));
				
				
			}
			
			connect.commit();
			
		} catch (SQLException E) {
			// TODO Auto-generated catch block
			E.printStackTrace();
		}
		
		return e;
	}

	
	
}
