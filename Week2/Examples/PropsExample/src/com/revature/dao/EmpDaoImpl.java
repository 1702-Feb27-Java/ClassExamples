package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.revature.pojo.Employee;

public class EmpDaoImpl implements EmpDAO {
	/**
	 * This class aims to implement the DAO and provide functionality for interacting
	 * with the database.
	 */
	
	private Connection conn;
	private PreparedStatement stmt;

	/**
	 * Note, I only implemented an insert function in this example.
	 */
	public Employee getEmpByID(int id) {
		return null;
	}

	public Employee getEmpByFName(String fname) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> getEmpsByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Any interaction will get a connection from the connection factory.
	 * Below we take in an Employee object and use the values stored within to 
	 * insert into the database.
	 * 
	 */
	public void insertEmp(Employee e) {
		try{
			conn = ConnectionFactory.getConnection(); //Grab the conneciton
			stmt = conn.prepareStatement("INSERT INTO Employee VALUES (null,?,?,?,?)");
			//Use a prepared statement for SQL Injection prevention
			
			stmt.setString(1, e.getFname());
			stmt.setString(2, e.getFname());
			stmt.setInt(3, e.getTitleID());
			stmt.setInt(4, e.getManagerID());
			//Set the values in order from first question mark to last (1 to 4)
			
			//Perform the insert.
			stmt.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			//Always close the streams. Utilize the utility class for the sake of omitting
			//duplicate code.
			DAOUtil.close(conn);
			DAOUtil.close(stmt);
		}
	}
}