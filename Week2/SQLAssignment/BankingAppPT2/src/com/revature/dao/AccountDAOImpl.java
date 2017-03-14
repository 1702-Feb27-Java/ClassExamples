package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.pojo.*;
import com.revature.connect.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public void applyForAccount(Users usr) {
		//the passing parameter will be from the users input from the keyboard
		//and the user_id from the user object to pass into the Account table 
		
		
		try(Connection con = new ConnectionUtil().getConnection();) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Pick your account");
			System.out.println("1: checking");
			System.out.println("2: savings");
			System.out.println("3: joints");
			System.out.print("Enter your choice: ");
			
			int user_input1 = scan.nextInt();
			
			if ( user_input1 == 1 ) {
				String sql = "INSERT INTO ACCOUNTS(TYPE_ID, BALANCE, STATUS_ID, RESOLVER) VALUES('" + 1 + "', '" + 0.01 + "', '" + 1 + "', '" + usr.getUser_id() + "')";
				Statement statement = con.createStatement();
				int numRowsAffected = statement.executeUpdate(sql);
				System.out.println("Num Rows Affected: " + numRowsAffected);
				//"', '" + usr.getUser_id() + "')";
				//"')";
				//System.out.println(usr.getUser_id());
				
			}
			
			else if ( user_input1 == 2 ) {
				String sql = "INSERT INTO ACCOUNTS(TYPE_ID, BALANCE, STATUS_ID, RESOLVER) VALUES('" + 2 + "', '" + 0.01 + "', '" + 1 + "', '" + usr.getUser_id() + "')";
				Statement statement = con.createStatement();
				int numRowsAffected = statement.executeUpdate(sql);
				System.out.println("Num Rows Affected: " + numRowsAffected);
				
			}
			
			else {
				
				String sql = "INSERT INTO ACCOUNTS(TYPE_ID, BALANCE, STATUS_ID, RESOLVER) VALUES('" + 3 + "', '" + 0.01 + "', '" + 1 + "', '" + usr.getUser_id() + "')";
				Statement statement = con.createStatement();
				int numRowsAffected = statement.executeUpdate(sql);
				System.out.println("Num Rows Affected: " + numRowsAffected);
				
			}
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void ViewAccountInfo(Users usr) {
		//passing the username from the user
		try(Connection con = new ConnectionUtil().getConnection();) {
			
			//table may be a join on both accounts and customer
			//String sql = "SELECT USER_ID, ACCOUNT_ID FROM CUSTOMER_ACCOUNTS WHERE USER_ID = " + number_id +  " ' and Account_id = ' " + Account_id;
			//Statement statement = con.createStatement();
			//int numRowsAffected = statement.executeUpdate(sql);
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
			
			
	}
		
}
