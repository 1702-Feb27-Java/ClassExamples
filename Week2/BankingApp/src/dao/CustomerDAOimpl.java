package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Account;
import util.ConnectionUtil;

public class CustomerDAOimpl implements CustomerDAO {

	// add customer account to user table

	public void addCustomer(String firstName, String lastName, String username, String password) {
		int numRows = 0;
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "CALL ADDCUSTOMER(?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, firstName);
			cs.setString(2, lastName);
			cs.setString(3, username);
			cs.setString(4, password);
			numRows = cs.executeUpdate();
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void openAnAccount(int userId, int typeId, int statusId) {

		int numRows = 0;
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "CALL ADD_ACCOUNT (?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setInt(1, userId);
			cs.setInt(2, typeId);
			cs.setInt(3, statusId);
			cs.execute();
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double getBalance(int accountId) {
		double balance = 0;

		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "call GETBALANCE(?,?)";
			CallableStatement cs = connect.prepareCall(sql);

			cs.registerOutParameter(1, java.sql.Types.DOUBLE);
			cs.setInt(2, accountId);
			cs.execute();

			balance = cs.getDouble(1);

			connect.commit();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());

		}
		return balance;
	}

	@Override

	// get the account type
	public String getAccountType(int typeId) {
		String accountType = "";

		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT type FROM account_Type WHERE type_id = ?";

			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, typeId);

			ResultSet rs = ps.executeQuery();
			rs.next();
			accountType = rs.getString(1);
			connect.commit();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return accountType;
	}

	@Override
	public String getAccountStatus(int statusId) {
		String status = "";

		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT status FROM status WHERE status_id = ?";

			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, statusId);

			ResultSet rs = ps.executeQuery();
			rs.next();
			status = rs.getString(1);
			connect.commit();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;
	}

	@Override
	public void deposit(int accountId, double amount) {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "update accounts set balance = ? where account_id = ?";
			CallableStatement ps = connect.prepareCall(sql);

			double total = getBalance(accountId);
			total += amount;
			ps.setDouble(1, total);
			ps.setInt(2, accountId);

			ps.executeUpdate();

			connect.commit();

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());

		}

	}

	@Override
	public void withdraw(int accountId, double amount) {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = " update accounts set balance=? where account_id=?";
			CallableStatement ps = connect.prepareCall(sql);

			double total = getBalance(accountId);
			total -= amount;
			ps.setDouble(1, total);
			ps.setInt(2, accountId);

			ps.executeUpdate();

			connect.getAutoCommit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	
	
	// check user's login by verifying their user's i vb
	public int checkLogin(String userName, String password){
		int userId = 0;
		try(Connection c = ConnectionUtil.getConnection();) {
			c.setAutoCommit(false);
			String sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";
			PreparedStatement cs = c.prepareCall(sql);
			cs.setString(1, userName);
			cs.setString(2, password);
			ResultSet rs = cs.executeQuery();
			rs.next();
			userId = rs.getInt(1);
			
			c.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userId;
	}

	
	}
	
	

	


