package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojo.User;
import com.revature.util.ConnectionUtil;

public class DAOCustomerImpl implements DAOCustomer{

	@Override
	public int addCustomer(String fn, String ln, String un, String pw) {
		int numRows = 0; 
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "CALL insertCustomer(?, ?, ?, ?)";
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
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users  = new ArrayList<User>();
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM Users";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				
				user.setUserId(rs.getInt(1));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setRoleId(rs.getInt(6));
				
				users.add(user);
				user = null;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public int applyForAccount(int userId, int actId, int typeId) {
		int numRows = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			double startingBalance = 0.01;
			
			String sql = "CALL APPLYFORACCOUNT(?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setInt(1, userId);
			cs.setInt(2, actId);
			cs.setInt(3, typeId);
			cs.setDouble(4, startingBalance);
			
			numRows = cs.executeUpdate();
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return numRows;
	}

	@Override
	public ArrayList<String> loginCustomer(String un, String pw) {
		ArrayList<String> customer= new ArrayList<String>();
		int customerId = 0;
		String password = "";
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "CALL LOGINCUSTOMER(?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.setString(2, un);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
		
			customerId = cs.executeUpdate();
			
			customerId = cs.getInt(1);
			password = cs.getString(3);
			
			connect.commit();	
			
			customer.add(Integer.toString(customerId));
			customer.add(password);

		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
				
		return customer;
	}
		
}
