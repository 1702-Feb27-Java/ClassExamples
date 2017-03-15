package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionUtil;

public class AdminDao {
	// login
	
	public int adminLogin(String username, String password) {
		
		int userid = 0;
		try (Connection c = ConnectionUtil.getConnection();) {
			c.setAutoCommit(false);
			String sql = "SELECT user_id FROM users WHERE username = ? AND password=?";
			PreparedStatement cs = c.prepareStatement(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				userid = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userid;
	}
	
	
	// add an employee
	
	
	
	
	public int checkStatus(int accountId){
		int status=0;
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT status_id FROM accounts WHERE account_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, accountId);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			status= rs.getInt(1);
			connect.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	
}
	public int getRoleid(int userid) {
		int roleid = 0;
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "select users.role_id from users where user_id=?";
			CallableStatement ps = connect.prepareCall(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roleid = rs.getInt(1);
			}
			System.out.println(roleid);
			connect.getAutoCommit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleid;
	}
	
	public int addEmployee(String firstName, String lastName, String username, String password) {
		int roleid = 2;
		int numRows = 0;
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "CALL ADDCUSTOMER(?, ?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, firstName);
			cs.setString(2, lastName);
			cs.setString(3, username);
			cs.setString(4, password);
			cs.setInt(5, roleid);
			numRows = cs.executeUpdate();
			connect.commit();
			return numRows;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Customer account created");
		return numRows;
		
	}
	
}
