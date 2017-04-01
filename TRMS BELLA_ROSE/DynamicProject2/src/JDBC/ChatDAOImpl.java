package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import Classes.ChatMessage;
import Classes.Employee;
import Classes.Reimbursement;

public class ChatDAOImpl implements ChatDAO
{
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement preStmt;
	private ResultSet rs;

	@Override
	public void insertMessage(ChatMessage per)
	{
		// TODO Auto-generated method stub
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Insert into Chat (note, reim_id, chat_id, author_id, sent_to_id) VALUES (?, ?, ?, ?, ?)";
			preStmt = conn.prepareStatement(query);

			String note = per.getNote();
			Integer reim_id = per.getReim_id(), chat_id = per.getChat_id(), author_id = per.getAuthor_id(), sentTo_id = per.getSent_to_id();
			
			// Fill out Statement Parameters
			preStmt.setString(1, note);
			preStmt.setInt(2, reim_id);
			preStmt.setInt(3, chat_id);
			preStmt.setInt(4, author_id);
			preStmt.setInt(5, sentTo_id);

			// Execute the Query
			preStmt.executeUpdate();

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
	public LinkedList<ChatMessage> getAllMessages()
	{
		// TODO Auto-generated method stub
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select * from Employee";
			stmt = conn.createStatement();

			// Execute the Query
			rs = stmt.executeQuery(query);
			
			LinkedList<ChatMessage> emps = new LinkedList<>();

			while (rs.next())
			{
				
				ChatMessage temp = new ChatMessage();
				
				temp.setNote(rs.getString("note"));
				temp.setReim_id(rs.getInt("reim_id"));
				temp.setChat_id(rs.getInt("chat_id"));
				temp.setAuthor_id(rs.getInt("author_id"));
				temp.setSent_to_id(rs.getInt("sent_to_id"));
				
				emps.add(temp);
				
			}
			return emps;

			
		} catch (

		Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}
		
		return null;
	}
	
	public LinkedList<ChatMessage> getMessagesByReimId(int id)
	{
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select * from Reimbursement where reimbursement_id = ?";
			preStmt = conn.prepareStatement(query);
			
			preStmt.setInt(1, id);

			// Execute the Query
			rs = preStmt.executeQuery();

			LinkedList<ChatMessage> emps = new LinkedList<>();

			while (rs.next())
			{
				
				ChatMessage temp = new ChatMessage();
				
				temp.setNote(rs.getString("note"));
				temp.setReim_id(rs.getInt("reim_id"));
				temp.setChat_id(rs.getInt("chat_id"));
				temp.setAuthor_id(rs.getInt("author_id"));
				temp.setSent_to_id(rs.getInt("sent_to_id"));
				
				emps.add(temp);
				
			}
			return emps;
			
		} catch (

		Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}
		
		return null;
	}

	@Override
	public void saveMessage(ChatMessage per)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMessageById(int id)
	{
		// TODO Auto-generated method stub

	}

}
