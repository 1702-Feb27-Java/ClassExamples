package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojo.Account;
import com.revature.util.ConnectionUtil;

public class DAOEmployeeImpl implements DAOEmployee{

	@Override
	public int addEmployee(String fn, String ln, String un, String pw) {
		int numRows = 0; 
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "CALL INSERTEMPLOYEE(?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, fn);
			cs.setString(2, ln);
			cs.setString(3, un);
			cs.setString(4, pw);
			
			numRows = cs.executeUpdate();
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return numRows;
	}

	
	@Override
	public ArrayList<String> loginEmployee(String un, String pw) {
		ArrayList<String> employee= new ArrayList<String>();
		int employeeId = 0;
		String password = "";
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "CALL LOGINEMPLOYEE(?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.setString(2, un);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
		
			employeeId = cs.executeUpdate();
			
			employeeId = cs.getInt(1);
			password = cs.getString(3);
			
			connect.commit();	
			
			employee.add(Integer.toString(employeeId));
			employee.add(password);

		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
				
		return employee;
	}



	@Override
	public ArrayList<Account> getUnapprovedAccounts() {
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM ACCOUNT"
					+ " WHERE STATUS_ID = 1";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Account ac = new Account();
				
				ac.setAccountId(rs.getInt(1));
				ac.setTypeId(rs.getInt(2));
				ac.setBalance(rs.getDouble(3));
				ac.setStatusId(rs.getInt(4));
				ac.setResolverId(rs.getInt(5));
				accounts.add(ac);
				
				ac = null;
			}
			connect.commit();
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}



	
}
