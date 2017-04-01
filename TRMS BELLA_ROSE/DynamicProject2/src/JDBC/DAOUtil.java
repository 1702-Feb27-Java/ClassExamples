package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtil {
	/**
	 * This class serves to offer helper methods for the sake of abstraction.
	 * Specifically this class will be utilized for closing streams and handling the possible
	 * exceptions.
	 * 
	 * It will overload the close method with all the possble stream objects that should be
	 * handled.
	 * 
	 * @param rs
	 */
	public static void close(ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Could not close result set!");
			}
		}
	}
	public static void close(Connection conn){
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Could not close connection!");
			}
		}
	}
	public static void close(Statement stmt){
		if(stmt!=null){
			try{
				stmt.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Could not close statement!");
			}
		}
	}
}
