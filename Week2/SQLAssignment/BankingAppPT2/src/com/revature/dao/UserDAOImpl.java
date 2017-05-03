package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.connect.*;
import com.revature.connect.*;
import com.revature.pojo.Users;


public class UserDAOImpl implements UserDAO {

	@Override
	public void UserSignUp(Users user) {
		
		try(Connection con = new ConnectionUtil().getConnection();) {
			 
			int id = user.getUser_id();
			String fname = user.getFirstname();
			String lname = user.getLastname();
			String usrname = user.getUsername();
			String psswrd = user.getPassword();
			int roleid = user.getRole_id();
			

			String sql = "INSERT INTO USERS(USER_ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD, ROLE_ID) VALUES ('" + id + "', '" 
			+ fname + "', '" + lname + "', '" + usrname + "', '" + psswrd + "', '" + roleid + "')";
			Statement statement = con.createStatement();

			int numRowsAffected = statement.executeUpdate(sql);
			System.out.println("Num Rows Affected: " + numRowsAffected);
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public int UserFirst_and_last_name(Users user, String fname, String lname) {
		
		int numRows = 0;
		
		
		try(Connection connect = ConnectionUtil.getConnection(); ) {
			connect.setAutoCommit(false);
			int id = user.getUser_id();
			
			//String sql = "UPDATE FLASH_CARDS set f_question = ' "  + q + " ' WHERE t_id = " + id;
			String sql = "UPDATE Users set FIRSTNAME = ' " + fname + " ', '" + "'LASTNAME = ' " + lname + " ' WHERE role_id = " + id;
			
			Statement statement = connect.createStatement();
			numRows = statement.executeUpdate(sql);
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return numRows;
		
	}

	@Override
	public int UpdateUsername_password(Users user, String usr, String pass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ValidateUser(String user_name, String Password) {
		
		try(Connection connect = ConnectionUtil.getConnection(); ) {
		
			String sql = "SELECT ROLE_ID, USERNAME, PASSWORD from USERS WHERE USERNAME = '"+ user_name +"' and password = '"+ Password +"'";
			
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			//System.out.println("here");
			
			while(rs.next() ) {
				return rs.getInt(1);
				
			}
			
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	

	
	
	
}
