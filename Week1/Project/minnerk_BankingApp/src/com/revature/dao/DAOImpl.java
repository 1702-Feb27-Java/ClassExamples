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
						
			while (rs.next()){
				System.out.println(s1 + " " + s2 + " " + i + " " + 2);
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4));
				String uname = rs.getString(1);
				String password = rs.getString(2);
				int role = rs.getInt(3);
				int status = rs.getInt(4);
				
				
				if(s1.equals(uname) && s2.equals(password) && role == i && status == 2){
					return false;
				}

			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static Customer getPersonInfo(String s){
		Customer c = new Customer();
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT u.USERID, u.FIRSTNAME, u.LASTNAME, u.USERNAME, u.PW, u.ROLEID, a.TYPEID, a.BALANCE FROM USERS u INNER JOIN CUSTOMERACCOUNTS ca ON u.USERID=ca.USERID INNER JOIN ACCOUNTS a ON ca.ACCTID=a.ACCTID WHERE USERNAME=?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				if(s.equals(rs.getString(1)));
					c = formatSet(rs, c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return c;
	}
		
	private static Customer formatSet(ResultSet rs, Customer c) {
		
		try {
			c.setUserID(rs.getInt(1));
			c.setFirstName(rs.getString(2));
			c.setLastName(rs.getString(3));
			c.setUserName(rs.getString(4));
			c.setPassword(rs.getString(5));			
			c.setRole(rs.getInt(6));
			if (rs.getInt(7) == 1)
				c.setCheckingAccountNumber(rs.getInt(7));
			else if (rs.getInt(7) == 2)
				c.setSavingsAccountNumber(rs.getInt(7));
			if (rs.getInt(8) == 1)
				c.setCheckingBalance(rs.getInt(8));
			else if (rs.getInt(8) == 2)
				c.setSavingsBalance(rs.getInt(8));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
}

