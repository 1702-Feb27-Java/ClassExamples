package com.revature.banking2.logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.banking2.logging.Log.Level;
import com.revature.banking2.util.ConnectionUtil;

public class LogDaoImpl implements LogDao {

	@Override
	public void addLog(Level level, String message) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String insertSql = "INSERT INTO Logs (level_id, message) VALUES (?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertSql);
			insertStatement.setInt(1, level.getLevelId());
			insertStatement.setString(2, message);
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
