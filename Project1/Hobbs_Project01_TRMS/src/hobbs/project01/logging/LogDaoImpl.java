package hobbs.project01.logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import hobbs.project01.logging.Log.Level;
import hobbs.project01.util.ConnectionUtil;

/**
 * Interfaces with the database to acess logs.
 * 
 * @author Michael Hobbs
 *
 */
public class LogDaoImpl implements LogDao {

	@Override
	public void addLog(Level level, String message) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String insertSql = "INSERT INTO Logs (id, message) VALUES (?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertSql);
			insertStatement.setInt(1, level.getId());
			insertStatement.setString(2, message);
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
