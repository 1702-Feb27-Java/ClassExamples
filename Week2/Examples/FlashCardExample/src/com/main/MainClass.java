package com.main;

import com.dao.DAO;
import com.dao.DAOImpl;
import com.domain.FlashCard;

public class MainClass {
	public static void main(String[] args) {
		
		
		FlashCard fc = new FlashCard("How did I get the auto generated keys in JDBC?", "It's easy. Check out my DAOImpl");
		
		DAO dao = new DAOImpl();
//		dao.createFlashCardPreparedStmt(fc);
		dao.createFcPreparedStmtGetPK(fc);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String username = "week1db";
//		String password = "p4ssw0rd";
//
//		// Step 1: Register Driver with DriverManager (not required in
//		// ojdbc7.jar)
//		// Class.forName("oracle.jdbc.OracleDriver");
//
//		// Step 2: Get Connection instance
//		try(Connection connection = DriverManager.getConnection(url, username, password);) {
//			
//			// Step 3: Get Statement instance
//			String sql = "INSERT INTO flash_cards(fc_question, fc_answer) VALUES ('What is Java?', 'Awesome')";
//			Statement statement = connection.createStatement();
//
//			//Step 4: execute Statement
//			int numRowsAffected = statement.executeUpdate(sql);
//
//			System.out.println("Num Rows Affected: " + numRowsAffected);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}
}
