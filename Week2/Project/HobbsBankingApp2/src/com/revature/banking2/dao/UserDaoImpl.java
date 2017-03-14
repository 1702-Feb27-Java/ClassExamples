package com.revature.banking2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.banking2.app.State;
import com.revature.banking2.pojo.User;
import com.revature.banking2.pojo.User.Role;
import com.revature.banking2.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void addUser(String username, String password, String firstName, String lastName, Role role) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String insertSql = "INSERT INTO Users (username, pw, first_name, last_name, role_id) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertSql);
			insertStatement.setString(1, username);
			insertStatement.setString(2, password);
			insertStatement.setString(3, firstName);
			insertStatement.setString(4, lastName);
			insertStatement.setInt(5, role.getId());
			insertStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateUser(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String updateSql = "UPDATE Users SET first_name = ?, last_name = ?, username = ?, pw = ?, role_id = ? WHERE user_id = ?";
			PreparedStatement updateStatement = connection.prepareStatement(updateSql);
			updateStatement.setString(1, user.getFirstName());
			updateStatement.setString(2, user.getLastName());
			updateStatement.setString(3, user.getUsername());
			updateStatement.setString(4, user.getPassword());
			updateStatement.setInt(5, user.getRole().getId());
			updateStatement.setInt(6, user.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<User> getUsers(User.Role role) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM USERS WHERE role_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setInt(1, role.getId());
			ResultSet users = selectStatement.executeQuery();
			ArrayList<User> usersList = new ArrayList<>();
			while (users.next()) {
				User user = new User();
				user.setUsername(users.getString("username"));
				user.setPassword(users.getString("pw"));
				user.setFirstName(users.getString("first_name"));
				user.setLastName(users.getString("last_name"));
				user.setId(users.getInt("user_id"));
				user.setRole(role);
				usersList.add(user);
			}
			State.getState().setUsers(usersList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return State.getState().getUsers();
	}
	
	@Override
	public void addAccount() {
		
	}

}
