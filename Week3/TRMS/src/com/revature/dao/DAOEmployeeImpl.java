package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class DAOEmployeeImpl implements DAOEmployee{

	@Override
	public boolean createEmployee(int dept, String firstName, String lastName, 
			String username, String password, String phone, String email) {
		boolean success = false;
		int numRows = 0;
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "CALL INSERTEMPLOYEE(?, ?, ?, ?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setInt(1, dept);
			cs.setString(2, firstName);
			cs.setString(3, lastName);
			cs.setString(4, username);
			cs.setString(5, password);
			cs.setString(6, phone);
			cs.setString(7, email);
			
			numRows = cs.executeUpdate();
			System.out.println(numRows);
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return success;
	}


		
}
