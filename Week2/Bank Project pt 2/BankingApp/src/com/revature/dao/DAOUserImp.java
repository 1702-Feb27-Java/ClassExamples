package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.revature.connection.ConnectionClass;
import com.revature.pojo.UserClass;

// implements all methods in the DAOUser interface
public class DAOUserImp implements DAOUser {

	// initialize statements here
	CallableStatement insert;
	PreparedStatement updateFirst, updateLast, updateUname, updatePW, getByID, getByUser;
	PreparedStatement getAll;

	@Override
	public void addUser(UserClass uc) {

		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			// calls the procedure in the database to add a new user
			insert = connect.prepareCall("CALL addUser(?, ?, ?, ?)");

			// set user input into the statement
			insert.setString(1, uc.getFirstName());
			insert.setString(2, uc.getLastName());
			insert.setString(3, uc.getUsername());
			insert.setString(4, uc.getPassword());

			// execute the callable statement
			insert.execute();

			System.out.println("Success! You've added a new user.");
			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}

	}

	@Override
	public void updateFirstName(UserClass uc, String f) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			// calls a statement to update the user's firstname
			updateFirst = connect.prepareStatement("UPDATE Users SET firstname = ? WHERE user_id = ?");

			updateFirst.setString(1, f);
			updateFirst.setInt(2, uc.getUserID());

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
		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			// calls a statement to update the user's lastname
			updateLast = connect.prepareStatement("UPDATE Users SET lastname = ? WHERE user_id = ?");

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
		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			// calls a statement to update the user's username
			updateUname = connect.prepareStatement("UPDATE Users SET uname = ? WHERE user_id = ?");

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
		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			// calls a statement to update the user's password
			updatePW = connect.prepareStatement("UPDATE Users SET pw = ? WHERE user_id = ?");

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

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			// calls a query to get all users
			String sql = "SELECT * FROM Users";
			getAll = connect.prepareStatement(sql);

			ResultSet rs = getAll.executeQuery();

			// then we save these users into an arraylist
			while (rs.next()) {
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
		return allUsers;
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

	@Override
	public Hashtable<String, String> getUsernamePW() {
		// TODO Auto-generated method stub

		Hashtable<String, String> logIn = new Hashtable<String, String>();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			// this will select all users
			String sql = "SELECT * FROM Users";
			getByUser = connect.prepareStatement(sql);

			ResultSet rs = getByUser.executeQuery();

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
	public UserClass getUserByID(int id) {
		// TODO Auto-generated method stub
		UserClass user = new UserClass();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			// calls a query to get a user by user id
			String sql = "SELECT * FROM Users WHERE user_id = ?";
			getByUser = connect.prepareStatement(sql);
			getByUser.setInt(1, id);

			ResultSet rs = getByUser.executeQuery();

			// then we save this user into a UserClass object
			while (rs.next()) {

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
