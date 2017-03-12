package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojo.Account;
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
			
			cs.executeUpdate();
			connect.commit();
			numRows++;
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

	@Override
	public ArrayList<Account> getAccounts(int userId) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM ACCOUNT, CUSTOMERACCOUNTS"
					+ " WHERE ACCOUNT.account_id = CUSTOMERACCOUNTS.account_id"
					+ " and CUSTOMERACCOUNTS.user_id = ?"
					+ " and ACCOUNT.status_id = 2";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, userId);
			
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
	public String getAccountType(int typeId) {
		String accountType = "";
		
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT type FROM ACCOUNTTYPE WHERE TYPE_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, typeId);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			accountType = rs.getString(1);
			connect.commit();
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accountType;
	}
	
	@Override
	public String getStatus(int statusId) {
		String statusType = "";
		
		try (Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT status FROM STATUS WHERE STATUS_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, statusId);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			statusType = rs.getString(1);
			connect.commit();
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return statusType;
	}
	
	public double getBalance(int accountId){
		double balance = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
		
			String sql = "CALL GETBALANCE(?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.setInt(1, accountId);
			cs.registerOutParameter(2, java.sql.Types.DOUBLE);
		
			cs.executeUpdate();
			
			connect.commit();	
			
			balance = cs.getDouble(2);
			
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return balance;
	}

	@Override
	public double setBalance(int accountId, double balance) {
		double newBalance = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "CALL SETBALANCE(?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.setInt(1, accountId);
			cs.setDouble(2, balance);
			
			cs.executeUpdate();
			
			connect.commit();	
			newBalance = balance; 
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return newBalance;
	}



		
}
