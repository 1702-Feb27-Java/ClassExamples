package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.connection.ConnectionClass;
import com.revature.pojo.UserClass;

public class DAOUserImp implements DAOUser {

	CallableStatement insert;
	PreparedStatement updateFirst, updateLast, updateUname, updatePW, getByID;
	PreparedStatement getAll;

	@Override
	public void addUser(UserClass uc) {

		try (Connection connect = ConnectionClass.getConnection();){
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);
			
			insert = connect
					.prepareCall("CALL addUser(?, ?, ?, ?)");
			
			insert.setString(1, uc.getFirstName());
			insert.setString(2, uc.getLastName());
			insert.setString(3, uc.getUsername());
			insert.setString(4, uc.getPassword());

			insert.execute();
			
			System.out.println("Success! You've added a new user.");
			connect.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2){
			e2.printStackTrace();
		}

	}

	@Override
	public void updateFirstName(int id, String f) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();){
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			updateFirst = connect
					.prepareStatement("UPDATE Users SET firstname = ? WHERE user_id = ?");
			
			updateFirst.setString(1, f);
			updateFirst.setInt(2, 3);
						
			updateFirst.executeUpdate();
			System.out.println("Success! You've updated the first name.");

			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void updateLastName(UserClass uc, String l) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();){
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			updateLast = connect
					.prepareStatement("UPDATE Users SET lastname = ? WHERE user_id = ?");
			
			updateLast.setString(1, l);
			updateLast.setInt(2, uc.getUserID());

			
			updateLast.executeUpdate();
			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void updateUsername(UserClass uc, String u) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();){
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			updateUname = connect
					.prepareStatement("UPDATE Users SET uname = ? WHERE user_id = ?");
			
			updateUname.setString(1, u);
			updateUname.setInt(2, uc.getUserID());

			
			updateUname.executeUpdate();
			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void updatePassword(UserClass uc, String pw) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();){
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			updatePW = connect
					.prepareStatement("UPDATE Users SET pw = ? WHERE user_id = ?");
			
			updatePW.setString(1, pw);
			updatePW.setInt(2, uc.getUserID());

			
			updatePW.executeUpdate();
			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public ArrayList<UserClass> getAllUsers() {
		// TODO Auto-generated method stub
		
		ArrayList<UserClass> allUsers = new ArrayList<UserClass>();
		
		try (Connection connect = ConnectionClass.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM Users";
			getAll = connect.prepareStatement(sql);
			
			ResultSet rs = getAll.executeQuery();
			
			while (rs.next()){
				UserClass user = new UserClass();
				
				user.setUserID(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setRoleID(rs.getInt(6));
				allUsers.add(user);
				
				user = null;
			}
			

			connect.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		allUsers.forEach(System.out::println);
		return allUsers;
	}

	@Override
	public UserClass getUserByID(int id) {
		// TODO Auto-generated method stub
		
		UserClass user = new UserClass();
		
		try (Connection connect = ConnectionClass.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM Users WHERE user_id = ?";
			getByID = connect.prepareStatement(sql);
			getByID.setInt(1, id);
			
			ResultSet rs = getByID.executeQuery();
			
			while (rs.next()){
				
				user.setUserID(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setRoleID(rs.getInt(6));
				
			}

			connect.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return user;
	}

}
