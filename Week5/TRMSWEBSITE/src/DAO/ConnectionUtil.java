package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	
	public static Connection getConnection() throws SQLException
	{
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	catch(Exception e)
	{
		
		
	}
		String url = "jdbc:oracle:thin:@//localhost:1521/xe";
		String username = "trms";
		String password = "admin";
	
		return DriverManager.getConnection(url,username,password);
	
		
		
	}
}