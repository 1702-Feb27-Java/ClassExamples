package com.rev.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rev.pojo.FlashCard;
import com.rev.util.ConnectionUtil;

public class DAOImp implements DAOInter {

	@Override
	public int addCard(String q, String a) {
		// TODO Auto-generated method stub

		int numRows = 0;

		try (Connection connect = ConnectionUtil.getConnection();) {

			connect.setAutoCommit(false);
			String sql = "CALL insertFlash(?, ?)";
			CallableStatement cs = connect.prepareCall(sql);

			cs.setString(1, q);
			cs.setString(2, a);

			numRows = cs.executeUpdate();

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRows;
	}

	@Override
	public FlashCard getCardByID(int id) {
		// TODO Auto-generated method stub

		try (Connection connect = ConnectionUtil.getConnection();) {

			connect.setAutoCommit(false);
			String sql = "SELECT * FROM flash_cards WHERE f_id = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			FlashCard temp = new FlashCard();
			while(rs.next()){
				temp.setId(rs.getInt(1));
				temp.setQuesiton(rs.getString(2));
				temp.setAnswer(rs.getString(3));
			}
			
			connect.commit();
			return temp;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateQuestion(FlashCard fc, String q) {
		// TODO Auto-generated method stub

		int numRows = 0;

		try (Connection connect = ConnectionUtil.getConnection();) {

			connect.setAutoCommit(false);
			int id = fc.getId();
			String sql = "UPDATE flash_cards SET f_question = '" + q + "' WHERE f_id = " + id;

			Statement statement = connect.createStatement();
			numRows = statement.executeUpdate(sql);

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRows;
	}

	@Override
	public int updateAnswer(FlashCard fc, String a) {
		// TODO Auto-generated method stub
		int numRows = 0;

		try (Connection connect = ConnectionUtil.getConnection();) {

			connect.setAutoCommit(false);
			
			int id = fc.getId();
			String sql = "UPDATE flash_cards SET f_answer = ? WHERE f_id = ?";

			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1, a);
			ps.setInt(2, id);
			
			ResultSet rs = ps.executeQuery();
			System.out.println("works");

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRows;
		
	}

	@Override
	public ArrayList<FlashCard> getAllCards() {
		// TODO Auto-generated method stub
		return null;
	}

}
