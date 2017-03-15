package com.revature.banking2.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.banking2.app.State;
import com.revature.banking2.pojo.Account;
import com.revature.banking2.pojo.User;
import com.revature.banking2.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {
	
	@Override
	public void addAccount(User user, Account.Type type) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String insertSql = "CALL CREATE_ACCOUNT_FOR_CUSTOMER(?, ?)";
			CallableStatement createAccountForCustomer = connection.prepareCall(insertSql);
			createAccountForCustomer.setInt("userId", user.getId());
			createAccountForCustomer.setInt("accountType", type.getId());
			createAccountForCustomer.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateAccount(Account account) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String updateSql;
			PreparedStatement updateStatement;
			if (account.getResolverId() == null) {
				updateSql = "UPDATE ACCOUNTS SET type_id = ?, balance = ?, status_id = ? WHERE account_id = ?";
				updateStatement = connection.prepareStatement(updateSql);
				updateStatement.setInt(1, account.getType().getId());
				updateStatement.setDouble(2, account.getBalance());
				updateStatement.setInt(3, account.getStatus().getId());
				updateStatement.setInt(4, account.getId());
			}
			else {
				updateSql = "UPDATE ACCOUNTS SET type_id = ?, balance = ?, status_id = ?, resolver_id = ? WHERE account_id = ?";
				updateStatement = connection.prepareStatement(updateSql);
				updateStatement.setInt(1, account.getType().getId());
				updateStatement.setDouble(2, account.getBalance());
				updateStatement.setInt(3, account.getStatus().getId());
				updateStatement.setInt(4, account.getResolverId());
				updateStatement.setInt(5, account.getId());
			}
			updateStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Account> getAccounts(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM CUSTOMER_ACCOUNTS WHERE USER_ID = ?)";
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setInt(1, user.getId());
			ResultSet accounts = selectStatement.executeQuery();
			ArrayList<Account> accountsList = new ArrayList<>();
			while (accounts.next()) {
				Account account = new Account();
				account.setId(accounts.getInt("account_id"));
				account.setType(Account.Type.getType(accounts.getInt("type_id")));
				account.setStatus(Account.Status.getStatus(accounts.getInt("status_id")));
				account.setBalance(accounts.getDouble("balance"));
				account.setResolverId(accounts.getInt("resolver_id"));
				accountsList.add(account);
			}
			State.getState().setAccounts(accountsList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return State.getState().getAccounts();
	}
	
}
