package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionUtil;

public class EmployeeDAO {

	public void seeAllCustomersAccounts() {
		ResultSet rs;

		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = " Select accounts.account_id, account_Type.type, accounts.balance from accounts "
					+ ""
					+ "inner join account_Type on  accounts.type_id=account_Type.TYPE_ID "
					+ "inner join customer_accounts on customer_accounts.account_id= accounts.account_id "
					+ "where accounts.status_id=3";

			CallableStatement cs = connect.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {

				System.out.printf("Account id: %d, Account Type : %s balance: %f \n", rs.getInt(1), rs.getString(2),
						rs.getDouble(3));
			}

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int EmployeeLogin(String username, String password) {

		int userid = 0;
		try (Connection c = ConnectionUtil.getConnection();) {
			c.setAutoCommit(false);
			String sql = "SELECT user_id FROM users WHERE username = ? AND password=?";
			PreparedStatement cs = c.prepareStatement(sql);
			cs.setString(1, username);
			cs.setString(2, password);

			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				userid = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userid;
	}

	/***
	 * Employee can approve account
	 * @param accountId the account id
	 */
	public void approveAccount(int accountId) {

		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "Update accounts set accounts.status_id=1 where accounts.account_id=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accountId);
			ps.executeQuery();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
