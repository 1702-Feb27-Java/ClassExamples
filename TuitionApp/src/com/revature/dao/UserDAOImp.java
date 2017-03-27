package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.revature.connection.ConnectionClass;
import com.revature.pojo.AppClass;
import com.revature.pojo.NotifClass;
import com.revature.pojo.UserClass;

public class UserDAOImp implements UserDAO {
	
	CallableStatement newUser, newApp;
	PreparedStatement getByUser, getUnamePW, getManagement;

	@Override
	public void addUser(UserClass uc) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			// calls the procedure in the database to add a new user
			newUser = connect.prepareCall("CALL addUser(?, ?, ?, ?, ?, ?, ?, ?)");

			// set user input into the statement
			newUser.setString(1, uc.getFirstname());
			newUser.setString(2, uc.getLastname());
			newUser.setString(3, uc.getUsername());
			newUser.setString(4, uc.getPw());
			newUser.setString(5,  uc.getEmail());
			newUser.setInt(6, uc.getDeptID());
			newUser.setInt(7,  uc.getRoleID());
			
			// logic for setting reportsto for the new user depending on
			// role and department
			if (uc.getDeptID() == 1){ // in Marketing
				if (uc.getRoleID() == 1){ // if an employee
					uc.setReportsto(2);
					newUser.setInt(8, uc.getReportsto());
				} else if (uc.getRoleID() == 2){ // if a supervisor
					uc.setReportsto(1);
					newUser.setInt(8, uc.getReportsto());
				} else { // if a dept head
					//uc.setReportsto();
					newUser.setNull(8, java.sql.Types.NUMERIC);
				}
			}
			else if (uc.getDeptID() == 2) { // in Human Resources
				if (uc.getRoleID() == 1){ // if an employee
					uc.setReportsto(3);
					newUser.setInt(8, uc.getReportsto());
				} else if (uc.getRoleID() == 2){ // if a supervisor
					uc.setReportsto(3);
					newUser.setInt(8, uc.getReportsto());
				} else { // if a dept head
					//uc.setReportsto(2);
					newUser.setNull(8, java.sql.Types.NUMERIC);
				}
			}
			else { // in Benco
				if (uc.getRoleID() == 1){ // if an employee
					uc.setReportsto(6);
					newUser.setInt(8, uc.getReportsto());
				} else if (uc.getRoleID() == 2){ // if a supervisor
					uc.setReportsto(5);
					newUser.setInt(8, uc.getReportsto());
				} else { // if a dept head
					//uc.setReportsto(2);
					newUser.setNull(8, java.sql.Types.NUMERIC);
				}
			}
			
			// execute the callable statement
			newUser.execute();

			//System.out.println("Success! You've added a new user.");
			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}

	}

	@Override
	public void addApp(UserClass uc, AppClass ac) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);
			
			newApp = connect.prepareCall("addApp(?, ?, ?, ?, ?, ?)");
			
			newApp.setInt(1, uc.getUserID());
			
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void addNotif(UserClass uc) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<AppClass> getAppsByUserID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NotifClass> getNotifByUserID() {
		// TODO Auto-generated method stub
		
		ArrayList<NotifClass> notifications = new ArrayList<NotifClass>();
		
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
			
			
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notifications;
	}

	@Override
	public Hashtable<String, String> getUsernamePW() {
		// TODO Auto-generated method stub
		
		Hashtable<String, String> logIn = new Hashtable<String, String>();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			// this will select all users
			String sql = "SELECT * FROM Users";
			getUnamePW = connect.prepareStatement(sql);

			ResultSet rs = getUnamePW.executeQuery();

			// then we save every username and password into a hashtable
			while (rs.next()) {
				logIn.put(rs.getString(4), rs.getString(5));
			}

			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logIn;
	}

	@Override
	public UserClass getUserByUsername(String username) {
		// TODO Auto-generated method stub

		UserClass user = new UserClass();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			// calls a query to return a user by username
			String sql = "SELECT * FROM Users WHERE uname = ?";
			getByUser = connect.prepareStatement(sql);
			getByUser.setString(1, username);

			ResultSet rs = getByUser.executeQuery();

			// then we save this user into a UserClass object
			while (rs.next()) {

				user.setUserID(rs.getInt(1));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPw(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setDeptID(rs.getInt(7));
				user.setRoleID(rs.getInt(8));
				user.setReportsto(rs.getInt(9));
				user.setAppID(rs.getInt(10));
				user.setNotifID(rs.getInt(11));

			}

			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}


//	@Override
//	public ArrayList<UserClass> getManagement() {
//		// TODO Auto-generated method stub
//		
//		ArrayList<UserClass> allManagement = new ArrayList<UserClass>();
//		
//		try (Connection connect = ConnectionClass.getConnection();) {
//			connect.setAutoCommit(false);
//
//			// calls a query to get all users
//			String sql = "SELECT * FROM Users WHERE role_id > 1";
//			getManagement = connect.prepareStatement(sql);
//
//			ResultSet rs = getManagement.executeQuery();
//
//			// then we save these users into an arraylist
//			while (rs.next()) {
//				UserClass user = new UserClass();
//
//				user.setUserID(rs.getInt(1));
//				user.setFirstname(rs.getString(2));
//				user.setLastname(rs.getString(3));
//				user.setUsername(rs.getString(4));
//				user.setPw(rs.getString(5));
//				user.setEmail(rs.getString(6));
//				user.setDeptID(rs.getInt(7));
//				user.setRoleID(rs.getInt(8));
//				user.setReportsto(rs.getInt(9));
//				allManagement.add(user);
//
//				user = null;
//			}
//
//			connect.commit();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return allManagement;
//	}

}
