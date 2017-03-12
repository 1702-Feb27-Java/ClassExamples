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



	@Override
	public boolean editAccountStatus(int actId, int statusId) {
		boolean editConfirm = false;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "CALL SETSTATUS(?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.setInt(1, actId);
			cs.setInt(2, statusId);
			
			cs.executeUpdate();
			
			connect.commit();	
			editConfirm = true;
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return editConfirm;
	}



	public boolean setResolverId(int empId, int actId){
		boolean confirmation = false;
		
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "CALL SETRESOLVERID(?, ?)";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, empId);
			ps.setInt(2, actId);
			
			ps.execute();

			connect.commit();
			confirmation = true;
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return confirmation;
	}


	
	@Override
	public ArrayList<Account> getAccounts(int empId) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM ACCOUNT WHERE RESOLVER_ID = ? ";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, empId);
			
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
	
	public boolean adminLogin(String un, String pw){
		boolean confirmation = false;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "CALL LOGINADMIN(?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.setString(2, un);
			cs.setString(3, pw);
		
			cs.execute();
			
			int adminId = cs.getInt(1);
			
			connect.commit();	

			if(adminId != 0){
				confirmation = true;
			}
			
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return confirmation;
	}
}
