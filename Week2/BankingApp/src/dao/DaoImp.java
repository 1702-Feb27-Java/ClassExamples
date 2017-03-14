package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.ConnectionUtil;

public class DaoImp implements Dao {

	public DaoImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	// add a customer or user to database
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

	// open an account for a user
	public void openAccount(int accountTypeID, double balance) {
		// join userid and accountid

		int numRows = 0;
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "CALL ADDACCOUNT (?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setInt(1, accountTypeID);
			cs.setDouble(2, balance);
			cs.execute();
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// get the balance from customer's account

	public double getBalance(int accountId) {
		double balance = 0;

		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "call getBalance(?, ?)";

			CallableStatement cs = connect.prepareCall(sql);
			cs.registerOutParameter(1, java.sql.Types.DOUBLE);
			cs.setInt(2, accountId);

			cs.execute();
			// ResultSet rs = cs.executeQuery();

			balance = cs.getDouble(1);
			// rs.next();
			System.out.println("balance " + balance);
			connect.commit();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());

		}
		return balance;
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
}
