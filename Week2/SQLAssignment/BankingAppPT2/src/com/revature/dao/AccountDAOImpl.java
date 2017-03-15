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
				//System.out.println("Num Rows Affected: " + numRowsAffected);
				//"', '" + usr.getUser_id() + "')";
				//"')";
				//System.out.println(usr.getUser_id());
				
			}
			
			else if ( user_input1 == 2 ) {
				String sql = "INSERT INTO ACCOUNTS(TYPE_ID, BALANCE, STATUS_ID, RESOLVER) VALUES('" + 2 + "', '" + 0.01 + "', '" + 1 + "', '" + usr.getUser_id() + "')";
				Statement statement = con.createStatement();
				int numRowsAffected = statement.executeUpdate(sql);
				//System.out.println("Num Rows Affected: " + numRowsAffected);
				
			}
			
			else {
				
				String sql = "INSERT INTO ACCOUNTS(TYPE_ID, BALANCE, STATUS_ID, RESOLVER) VALUES('" + 3 + "', '" + 0.01 + "', '" + 1 + "', '" + usr.getUser_id() + "')";
				Statement statement = con.createStatement();
				int numRowsAffected = statement.executeUpdate(sql);
				//System.out.println("Num Rows Affected: " + numRowsAffected);
				
			}
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Users ViewAccountInfo(Users usr) {
		
		try(Connection con = new ConnectionUtil().getConnection();) {
			Users this_user = new Users();
			//table may be a join on both accounts and customer
			String sql = "Select users.firstname, users.lastname, users.username, accounts.balance, account_type.type, accounts.account_id, accounts.resolver, accounts.status_id, accounts.type_id from Users"
						+ " join customer_accounts"
						+ " on users.user_id = customer_accounts.USER_ID"
						+ " join ACCOUNTS on ACCOUNTS.ACCOUNT_ID = customer_accounts.ACCOUNT_ID"
						+ " join account_type on accounts.type_id = account_type.type_id"
						+ " where Users.USERNAME = + ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, usr.getUsername() );
			ResultSet numRS = statement.executeQuery();
			
			while ( numRS.next() ) {
				System.out.println("First Name: " + numRS.getString(1));
				System.out.println("Last Name: " + numRS.getString(2));
				System.out.println("User Name: " + numRS.getString(3));
				System.out.println("Account Balance: " + numRS.getDouble(4));
				//System.out.println("Account type: " + numRS.getString(1));
				
				this_user.setFirstname(numRS.getString(1));
				this_user.setLastname(numRS.getString(2));
				this_user.setUsername(numRS.getString(3));
				this_user.a = new Accounts(numRS.getInt(6), numRS.getInt(9), numRS.getInt(4), numRS.getInt(8), numRS.getInt(7));
			}
			
			return this_user;
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
			
		return null;
	}
		
}
