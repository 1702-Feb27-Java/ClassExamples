package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import Accounts.*;

public class AccountDaoImpl implements AccountDAO
{

	private Connection conn;
	private Statement stmt;
	private PreparedStatement preStmt;
	private ResultSet rs;

	@Override
	public void insertAccount(Account acc)
	{
		// TODO Auto-generated method stub

		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Insert into accounts (account_id, type_id, balance, status_id) VALUES (null, ?, ?, ?)";
			preStmt = conn.prepareStatement(query);

			// Fill out Statement Parameters
			if (acc instanceof Checking)
			{
				preStmt.setInt(1, 1);

			} else if (acc instanceof Savings)
			{
				preStmt.setInt(1, 2);
			}

			preStmt.setDouble(2, acc.getBalance());
			preStmt.setInt(3, 1);

			// Execute the Query
			rs = stmt.executeQuery(query);

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}

	}

	@Override
	public List<Account> getAllAccounts()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByType(int role)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByUserID(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAccount(Account acc)
	{
		// TODO Auto-generated method stub

		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Update accounts set balance = ?, status_id = ? where account_id = ?";
			preStmt = conn.prepareStatement(query);

			// Fill out Statement Parameters
			preStmt.setDouble(1, acc.getBalance());

			if (acc.isApproved() == false)
			{
				preStmt.setInt(2, 1);
			} else
			{
				preStmt.setInt(2, 2);
			}
			
			preStmt.setInt(3, acc.getAccId());

			// Execute the Query
			rs = stmt.executeQuery(query);

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}

	}

	@Override
	public void deleteAccountByTypeAndUserId(int type, int id)
	{
		// TODO Auto-generated method stub

	}

}
