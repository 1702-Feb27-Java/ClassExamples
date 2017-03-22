package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.domain.FlashCard;
import com.util.ConnectionUtil;

public class DAOImpl implements DAO {
	
	@Override
	public void createFlashCard(FlashCard flashCard) {

		try (Connection connection = ConnectionUtil.getConnection();) {

			String q = flashCard.getQuestion();
			String a = flashCard.getAnswer();

			String sql = "INSERT INTO flash_cards(fc_question, fc_answer) VALUES ('" + q + "', '" + a +"')";
			Statement statement = connection.createStatement();

			int numRowsAffected = statement.executeUpdate(sql);
			System.out.println("Num Rows Affected: " + numRowsAffected);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public FlashCard retrieveFlashCardById(int id) {
		return null;
	}

	@Override
	public List<FlashCard> retrieveAllFlashCards() {
		return null;
	}

	@Override
	public void updateFlashCard(FlashCard flashCard) {

	}

	@Override
	public void deleteFlashCard(int id) {

	}

	@Override
	public void createFlashCardPreparedStmt(FlashCard flashCard) {
		try (Connection connection = ConnectionUtil.getConnection();) {
			
//			connection.setAutoCommit(false);
			
			String sql = "INSERT INTO flash_cards(fc_question, fc_answer) VALUES (?, ?)";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setString(1, flashCard.getQuestion());
			preparedStatment.setString(2, flashCard.getAnswer());
			
			int num = preparedStatment.executeUpdate();
		
//			connection.commit();
			
			System.out.println("num affected: " + num);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void callHelloWorldProc() {
		
	}

	/*
	 * Get the auto-generated primary key
	 */
	@Override
	public void createFcPreparedStmtGetPK(FlashCard flashCard) {
		try (Connection connection = ConnectionUtil.getConnection();) {
			
			//STEP 1: Define String[] of the fields that make up the primary key
			String[] primaryKeys = new String[1];
			primaryKeys[0] = "fc_id";
			
			String sql = "INSERT INTO flash_cards(fc_question, fc_answer) VALUES (?, ?)";
			
			/*
			 * STEP 2: call overloaded connection.prepareStatment method, 
			 * 			which takes our String
			 */
			PreparedStatement preparedStatment = connection.prepareStatement(sql, primaryKeys);
			preparedStatment.setString(1, flashCard.getQuestion());
			preparedStatment.setString(2, flashCard.getAnswer());
			
			preparedStatment.executeUpdate();
			
			/*
			 * STEP 3: After calling preparedStatement.executeUpdate() 
			 * 			-> call preparedStatement.getGeneratedKeys()
			 * 
			 * NOTE: This method returns a ResultSet
			 */
			ResultSet generatedKeys = preparedStatment.getGeneratedKeys();
			System.out.println("Generated keys:");
			while(generatedKeys.next()){
				System.out.println(generatedKeys.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
