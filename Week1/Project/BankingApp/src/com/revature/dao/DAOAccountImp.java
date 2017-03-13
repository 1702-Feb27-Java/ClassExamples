package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.connection.ConnectionClass;
import com.revature.pojo.AccountClass;
import com.revature.pojo.UserClass;

public class DAOAccountImp implements DAOAccount {

	CallableStatement newAccount;
	PreparedStatement updateBalance, updateStatus, getAccount;
	PreparedStatement getAll;

	@Override
	public void addAccount(AccountClass ac, int customerID) {
		// TODO Auto-generated method stub

		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			newAccount = connect.prepareCall("CALL addAccounts(?, ?)");

			newAccount.setInt(1, ac.getTypeID());
			newAccount.setInt(2, customerID);

			newAccount.execute();
			System.out.println("Success! You've added a new account.");
			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBalance(AccountClass ac, double balance) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();){
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			updateBalance = connect
					.prepareStatement("UPDATE Accounts SET balance = ? WHERE account_id = ?");
			
			updateBalance.setDouble(1, balance);
			updateBalance.setInt(2, ac.getAccountID());

			
			updateBalance.executeUpdate();
			System.out.println("Success! You've updated the balance.");
			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void updateStatus(AccountClass ac, int status) {
		// TODO Auto-generated method stub
		
		try (Connection connect = ConnectionClass.getConnection();){
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			updateStatus = connect
					.prepareStatement("UPDATE Accounts SET status_id = ? WHERE account_id = ?");
			
			updateStatus.setDouble(1, status);
			updateStatus.setInt(2, ac.getAccountID());

			
			updateStatus.executeUpdate();
			System.out.println("Success! You've updated the status of this account.");
			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public ArrayList<AccountClass> getAllAccounts() {
		// TODO Auto-generated method stub

		ArrayList<AccountClass> allAccounts = new ArrayList<AccountClass>();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM Accounts";
			getAll = connect.prepareStatement(sql);

			ResultSet rs = getAll.executeQuery();

			while (rs.next()) {
				AccountClass account = new AccountClass();

				account.setAccountID(rs.getInt(1));
				account.setTypeID(rs.getInt(2));
				account.setBalance(rs.getInt(3));
				account.setStatusID(rs.getInt(4));
				account.setResolverID(rs.getInt(5));
				allAccounts.add(account);

				account = null;
			}

			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		allAccounts.forEach(System.out::println);
		return allAccounts;
	}

	@Override
	public ArrayList<AccountClass> getAccountsByUserID(int id) {
		// TODO Auto-generated method stub
		ArrayList<AccountClass> accountsByID = new ArrayList<AccountClass>();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT ac.ACCOUNT_ID,ac.TYPE_ID, ac.BALANCE, ac.STATUS_ID, ac.RESOLVER_ID FROM Accounts ac INNER JOIN CustomerAccounts ca ON  ac.account_id = ca.acct_id AND ca.customer_id = ?";
			getAll = connect.prepareStatement(sql);
			getAll.setInt(1, id);
			
			ResultSet rs = getAll.executeQuery();

			while (rs.next()) {
				AccountClass account = new AccountClass();

				account.setAccountID(rs.getInt(1));
				account.setTypeID(rs.getInt(2));
				account.setBalance(rs.getInt(3));
				account.setStatusID(rs.getInt(4));
				account.setResolverID(rs.getInt(5));
				accountsByID.add(account);

				account = null;
			}

			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		accountsByID.forEach(System.out::println);
		return accountsByID;
	}

	@Override
	public AccountClass getAccountByID(int accountID) {
		// TODO Auto-generated method stub
		
		AccountClass ac = new AccountClass();
		
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM Accounts WHERE account_id = ?";
			getAccount = connect.prepareStatement(sql);
			getAccount.setInt(1, accountID);
			
			ResultSet rs = getAccount.executeQuery();

			while (rs.next()) {

				ac.setAccountID(rs.getInt(1));
				ac.setTypeID(rs.getInt(2));
				ac.setBalance(rs.getInt(3));
				ac.setStatusID(rs.getInt(4));
				ac.setResolverID(rs.getInt(5));

			}

			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ac;
	}

}
