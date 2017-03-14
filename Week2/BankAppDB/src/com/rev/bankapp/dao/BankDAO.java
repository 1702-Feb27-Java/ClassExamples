package com.rev.bankapp.dao;

import java.sql.*;

import com.rev.bankapp.connector.DBConnector;;

public class BankDAO {
	private static int accountCounter = 1;
	
	//creates a new user row
	public static void createNewUser(String fname, String lname, String username, String password, int roleID){
		
		try(Connection c = DBConnector.getConnection()){
			String sql = "INSERT INTO USERS(FIRST_NAME, LAST_NAME, USERNAME, PW, ROLE_ID) VALUES(?,?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setInt(5, roleID);
			
			ps.executeQuery();
		
		}catch(Exception e){
			System.out.println("INSERT USER FAILED " + e);
		}
		
	}
	
	//deletes user
	public static void deleteUser(int userID){
		
		try(Connection c = DBConnector.getConnection()){
			
			String sql = "DELETE FROM USERS WHERE USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.executeQuery();

		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	
	//returns a users full information
	public static String getUserString(int userID){
		
		String user = "";
		try(Connection c = DBConnector.getConnection()){
			
			String sql = "SELECT * FROM USERS WHERE USERS.USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				user += rs.getString(1) + ":";
				user += rs.getString(2) + ":";
				user += rs.getString(3) + ":";
				user += rs.getString(4) + ":";
				user += rs.getString(5) + ":";
				user += rs.getString(6);
			}
			
			
		}catch(Exception e){
			System.out.println("Get user string failed " + e);
		}
		
		return user;
	}
	
	public static void requestAccount(int userID, int accountTypeID){
		
		try(Connection c = DBConnector.getConnection()){
			c.setAutoCommit(false);
			String sql = "INSERT INTO ACCOUNTS(TYPE_ID, BALANCE, STATUS_ID) VALUES(?,0,1)";
			PreparedStatement pc = c.prepareStatement(sql);
			pc.setInt(1, accountTypeID);
			pc.executeQuery();
			
			sql = "INSERT INTO USER_ACCOUNTS(USER_ID) VALUES(?)";
			pc = c.prepareStatement(sql);
			pc.setInt(1, userID);
			pc.executeQuery();
			
			c.commit();
			c.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean getUsername(String username){
		
		boolean userExists = false;
		try(Connection c = DBConnector.getConnection()){
		
			String sql = "SELECT COUNT(USERS.USERNAME) FROM USERS WHERE USERS.USERNAME = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			username = username.toLowerCase();
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while(rs.next()){
				count = Integer.parseInt(rs.getString(1));
				System.out.println(count);
			}
			System.out.println(count);
			if(count > 0){
				userExists = true;
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		return userExists;		
	}

}
