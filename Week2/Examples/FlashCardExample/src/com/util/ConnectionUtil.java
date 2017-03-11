package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "FLASH_CARD";
		String password = "revature";
		
		return DriverManager.getConnection(url, username, password);
	}
	
	
}
