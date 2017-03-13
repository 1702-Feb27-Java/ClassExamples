package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bankingapp.Customer;
import com.revature.util.FactoryConnection;

public class DAOImpl {

	public static int insertData(Customer c) {
		// TODO Auto-generated method stub
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "CALL insert_new_person(?, ?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, c.getFirstName());
			cs.setString(2, c.getLastName());
			cs.setString(3, c.getUserName());
			cs.setString(4, c.getPassword());
			cs.setInt(5, c.getRole());
			int numRows = cs.executeUpdate();
			connect.commit();
			connect.close();
			return numRows;
		} catch (SQLException e) {
				e.printStackTrace();
		}
			return 0;
	}
	
	public static boolean verifyInfo(String s) {

		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT USERNAME FROM USERS WHERE USERNAME=?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()){
				if(s.equals(rs.getString(1)));{
					connect.commit();
					connect.close();
					return true;	
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
		
	public static boolean loginVerification(String s1, String s2, int i) {
		
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT u.USERNAME, u.PW, u.ROLEID, a.STATUSID FROM USERS u INNER JOIN CUSTOMERACCOUNTS ca ON u.USERID=ca.USERID INNER JOIN ACCOUNTS a ON ca.ACCTID=a.ACCTID WHERE USERNAME=?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, s1);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				String uname = rs.getString(1);
				String password = rs.getString(2);
				int role = rs.getInt(3);
				int status = rs.getInt(4);
				if(s1.equals(uname) && s2.equals(password) && role == i && status == 2){
					return true;
				}
			}
			connect.close();
			return false;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Customer getPersonInfo(String s){
		Customer c = new Customer();
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT u.USERID, u.FIRSTNAME, u.LASTNAME, u.USERNAME, u.PW, u.ROLEID, a.STATUSID, a.TYPEID, a.BALANCE FROM USERS u INNER JOIN CUSTOMERACCOUNTS ca ON u.USERID=ca.USERID INNER JOIN ACCOUNTS a ON ca.ACCTID=a.ACCTID WHERE USERNAME=?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				if(s.equals(rs.getString(1)));
					c = formatSet(rs, c);
			}
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return c;
	}
		
	public static Customer formatSet(ResultSet rs, Customer c) {
		
		try {
			c.setUserID(rs.getInt(1));
			c.setFirstName(rs.getString(2));
			c.setLastName(rs.getString(3));
			c.setUserName(rs.getString(4));
			c.setPassword(rs.getString(5));			
			c.setRole(rs.getInt(6));
			c.setApproved(rs.getInt(7));
			if (rs.getInt(8) == 1)
				c.setCheckingAccountNumber(rs.getInt(8));
			else if (rs.getInt(8) == 2)
				c.setSavingsAccountNumber(rs.getInt(8));
			if (rs.getInt(8) == 1)
				c.setCheckingBalance(rs.getDouble(9));
			else if (rs.getInt(8) == 2)
				c.setSavingsBalance(rs.getDouble(9));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public static int changePersonalInfo(int i, Customer c){
		// TODO Auto-generated method stub
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "CALL updatename(?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			
			if (i == 1){
				cs.setString(1, c.getFirstName());
			}
			else if (i==2){
				cs.setString(1, c.getLastName());
			}
			else if (i==3){
				cs.setString(1, c.getPassword());
			}
			cs.setInt(2, c.getUserID());
			cs.setInt(3, i);
			cs.executeUpdate();
			connect.commit();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int makeAccount(Customer c, int i){
		
		Connection connect = FactoryConnection.getConnection();
		
		try {
			connect.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "CALL make_account(?, ?)";
			try {
				CallableStatement cs = connect.prepareCall(sql);
				cs.setInt(1, i);
				cs.setInt(2, c.getUserID());
				cs.executeUpdate();
				connect.commit();
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return 0;
	}
	
	public static int accountTransaction(Customer c, int accountType, double amount){
		
		Connection connect = FactoryConnection.getConnection();
		try {
			connect.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "CALL account_transaction(?, ?, ?)";
			try {
				CallableStatement cs = connect.prepareCall(sql);
				cs.setInt(1, c.getUserID());
				cs.setInt(2, accountType);
				cs.setDouble(3, amount);
				cs.executeUpdate();
				connect.commit();
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return 0;
	}
	
	
	
	
	
	
	
	
}

