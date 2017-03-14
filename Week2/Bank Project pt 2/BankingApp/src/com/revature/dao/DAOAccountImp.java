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

// implementing all the methods in the DAOAccount interface
public class DAOAccountImp implements DAOAccount {

	// creating statements 
	CallableStatement newAccount;
	PreparedStatement updateBalance, updateStatus, getAccount;
	PreparedStatement getAll;

	@Override
	public void addAccount(int accountType, UserClass uc) {
		// TODO Auto-generated method stub

		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			// calls the addAccounts method in the database procedure
			newAccount = connect.prepareCall("CALL addAccounts(?, ?)");

			// set variables into the callable statement
			newAccount.setInt(1, accountType);
			newAccount.setInt(2, uc.getUserID());

			newAccount.execute();
			System.out.println("Congrats!");
			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBalance(AccountClass ac, double balance) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			// calls a query to update the account
			updateBalance = connect.prepareStatement("UPDATE Accounts SET balance = ? WHERE account_id = ?");

			// sets variable into the prepared statement
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
	public void updateStatus(AccountClass ac, int status, int resolver) {
		// TODO Auto-generated method stub

		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			// calls a query to approve accounts
			updateStatus = connect.prepareStatement("UPDATE Accounts SET status_id = ?, , resolver_id = ?, WHERE account_id = ?");

			// sets variables into the statement
			updateStatus.setInt(1, status);
			updateStatus.setInt(2, resolver);
			updateStatus.setInt(3, ac.getAccountID());

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

			// prepares statement to select all accounts
			String sql = "SELECT * FROM Accounts";
			getAll = connect.prepareStatement(sql);

			ResultSet rs = getAll.executeQuery();

			// we save all the accounts into an arraylist
			while (rs.next()) {
				AccountClass account = new AccountClass();

				account.setAccountID(rs.getInt(1));
				account.setTypeID(rs.getInt(2));
				account.setBalance(rs.getInt(3));
				account.setStatusID(rs.getInt(4));
				account.setResolverID(rs.getInt(5));
				// save the AccountClass object into the arraylist
				allAccounts.add(account);

				account = null;
			}

			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allAccounts;
	}

	@Override
	public ArrayList<AccountClass> getAccountsByUserID(int id) {
		// TODO Auto-generated method stub
		ArrayList<AccountClass> accountsByID = new ArrayList<AccountClass>();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			// calls the statement to return all accounts by ID
			String sql = "SELECT ac.ACCOUNT_ID,ac.TYPE_ID, ac.BALANCE, ac.STATUS_ID, ac.RESOLVER_ID FROM Accounts ac INNER JOIN CustomerAccounts ca ON  ac.account_id = ca.acct_id AND ca.customer_id = ?";
			getAll = connect.prepareStatement(sql);
			getAll.setInt(1, id);

			ResultSet rs = getAll.executeQuery();

			// then we save all the accounts into an arraylist
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
		return accountsByID;
	}

	@Override
	public AccountClass getAccountByID(int accountID) {
		// TODO Auto-generated method stub

		AccountClass ac = new AccountClass();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			// calls a statement to get one account from an account_id
			String sql = "SELECT * FROM Accounts WHERE account_id = ?";
			getAccount = connect.prepareStatement(sql);
			getAccount.setInt(1, accountID);

			ResultSet rs = getAccount.executeQuery();
			
			// we save this account into an AccountClass object
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

	@Override
	public ArrayList<AccountClass> getAccountsByStatus(int status) {
		// TODO Auto-generated method stub
		ArrayList<AccountClass> ac = new ArrayList<AccountClass>();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			// calls statement to get all accounts by status
			String sql = "SELECT * FROM Accounts WHERE status_id = ?";
			getAccount = connect.prepareStatement(sql);
			getAccount.setInt(1, status);

			ResultSet rs = getAccount.executeQuery();

			// then we save this into an arraylist
			while (rs.next()) {
				AccountClass account = new AccountClass();

				account.setAccountID(rs.getInt(1));
				account.setTypeID(rs.getInt(2));
				account.setBalance(rs.getInt(3));
				account.setStatusID(rs.getInt(4));
				account.setResolverID(rs.getInt(5));
				
				ac.add(account);
				account = null;
			}

			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ac;
	}

}
